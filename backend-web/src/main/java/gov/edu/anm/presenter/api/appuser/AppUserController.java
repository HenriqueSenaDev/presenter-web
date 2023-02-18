package gov.edu.anm.presenter.api.appuser;

import java.net.URI;

import java.util.List;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;
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

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class AppUserController {
	private final AppUserService appUserService;

	@GetMapping
	public ResponseEntity<List<AppUserOutputDto>> findAllUsers() {
		return ResponseEntity.ok().body(appUserService.findAllUsers());
	}

	@GetMapping(value = "/{username}")
	public AppUserOutputDto findUserByUsername(@PathVariable String username) {
		return appUserService.findUserByUsername(username);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AppUserOutputDto> findUserById(@PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.findUserById(id));
	}

	@GetMapping(value = "/findByToken")
	public AppUserOutputDto findUserByAccessToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The refresh token is missing.");
		}

		final String token = authorizationHeader.substring("Bearer ".length());
        return appUserService.findUserByToken(token);
	}

	@GetMapping(value = "/participations/{id}")
	public ResponseEntity<List<ParticipationOutputDto>> findUserParticipations(@PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.findUserParticipations(id));
	}

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody AppUser appUser) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveUser(appUser));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AppUserOutputDto> updateUser(@RequestBody AppUser appUser, @PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.updateUser(appUser, id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		appUserService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
