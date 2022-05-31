package gov.edu.anm.presenter.resources;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Participation;
import gov.edu.anm.presenter.entities.AppRole;
import gov.edu.anm.presenter.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AppUserResource {
    private final UserService userService;

    // AppUser methods
    @GetMapping(value = "/appusers")
    public ResponseEntity<AppUser> findUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @GetMapping(value = "/appusers/{id}")
    public ResponseEntity<AppUser> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @GetMapping(value = "/appusers/roles/{username}")
    public ResponseEntity<List<AppRole>> findUserAppRoles(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.findUserAppRoles(username));
    }

    @GetMapping(value = "/appusers/all")
    public ResponseEntity<List<AppUser>> findAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @GetMapping(value = "/appusers/participations/{id}")
    public ResponseEntity<List<Participation>> findUserParticipations(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findUserParticipations(id));
    }

    @PostMapping(value = "/appusers")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/appUsers").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(appUser));
    }

    @PutMapping(value = "/appusers/{id}")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser appUser, @PathVariable Long id) {
        return ResponseEntity.ok().body(userService.updateUser(appUser, id));
    }

    @DeleteMapping(value = "/appusers/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body("The user has been deleted.");
    }

    @GetMapping(value = "/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser appUser = userService.getUserByUsername(username);
                String access_token = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",
                                appUser.getRoles().stream()
                                        .map(AppRole::getName)
                                        .collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(403);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("The refresh token is missing.");
        }
    }

    // AppRoles methods
    @GetMapping(value = "/approles/{id}")
    public ResponseEntity<AppRole> findRoleById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findRoleById(id));
    }

    @GetMapping(value = "/approles")
    public ResponseEntity<List<AppRole>> findAllAppRoles() {
        return ResponseEntity.ok().body(userService.findAllAppRoles());
    }

    @PostMapping(value = "/approles")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/approles").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping(value = "/approles/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/approles/{id}")
    public ResponseEntity<AppRole> updateAppRole(@PathVariable Long id, @RequestBody AppRole appRole) {
        return ResponseEntity.ok().body(userService.updateAppRole(appRole, id));
    }

    @PutMapping(value = "/approles/removeofuser")
    public ResponseEntity<?> removeRoleOfUser(@RequestBody RoleToUserForm form) {
        userService.removeRoleOfUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().body("The AppRole has been removed of the AppUser.");
    }

    @DeleteMapping(value = "/approles/{id}")
    public ResponseEntity<?> deleteAppRole(@PathVariable Long id) {
        userService.deleteAppRole(id);
        return ResponseEntity.ok().body("The AppRole has been deleted.");
    }

}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
