package gov.edu.anm.presenter.api.appuser;

import java.net.URI;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gov.edu.anm.presenter.api.participation.Participation;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class AppUserController {
	private final AppUserService appUserService;

	@GetMapping
	public ResponseEntity<List<AppUser>> findAllUsers() {
		return ResponseEntity.ok().body(appUserService.findAllUsers());
	}

	@GetMapping(value = "/{username}")
	public AppUser findUserByUsername(@PathVariable String username) {
		return appUserService.findUserByUsername(username);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AppUser> findUserById(@PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.findUserById(id));
	}

	@GetMapping(value = "/findByToken")
	public AppUser findUserByAccessToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The refresh token is missing.");
		}

		final String token = authorizationHeader.substring("Bearer ".length());
        return appUserService.findUserByToken(token);
	}

	@GetMapping(value = "/participations/{id}")
	public ResponseEntity<List<Participation>> findUserParticipations(@PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.findUserParticipations(id));
	}

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody AppUser appUser) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/appUsers").toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveUser(appUser));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AppUser> updateUser(@RequestBody AppUser appUser, @PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.updateUser(appUser, id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		appUserService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
