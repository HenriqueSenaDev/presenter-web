package gov.edu.anm.presenter.api.admin;

import java.net.URI;

import java.util.List;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserInputDto;
import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;
import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class AppUserController {
	private final AppUserService appUserService;

	@GetMapping
	public ResponseEntity<List<AppUserOutputDto>> findAllUsers() {
		return ResponseEntity.ok().body(appUserService.findAllUsers());
	}

	@GetMapping(value = "/username/{username}")
	public AppUserOutputDto findUserByUsername(@PathVariable String username) {
		return appUserService.findUserByUsername(username);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AppUserOutputDto> findUserById(@PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.findUserById(id));
	}

	@GetMapping(value = "/participations/{id}")
	public ResponseEntity<List<UserParticipationOutputDto>> findUserParticipations(@PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.findUserParticipations(id));
	}

	@PostMapping
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUserInputDto appUserInputDto) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveUser(appUserInputDto));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AppUser> updateUser(@RequestBody AppUserInputDto appUserInputDto, @PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.updateUser(appUserInputDto, id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		appUserService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
