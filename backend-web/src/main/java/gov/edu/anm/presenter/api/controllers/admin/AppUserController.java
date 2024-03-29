package gov.edu.anm.presenter.api.controllers.admin;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserInputDto;
import gov.edu.anm.presenter.api.common.utils.ApiUtilities;
import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserService;
import gov.edu.anm.presenter.domain.participation.Participation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin/users")
public class AppUserController {
	private final AppUserService appUserService;

	@GetMapping
	public ResponseEntity<List<AppUser>> findAllUsers() {
		return ResponseEntity.ok().body(appUserService.findAllUsers());
	}

	@GetMapping(value = "/username/{username}")
	public AppUser findUserByUsername(@PathVariable String username) {
		return appUserService.findUserByUsername(ApiUtilities.convertStringPath(username));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AppUser> findUserById(@PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.findUserById(id));
	}

	@GetMapping(value = "/{id}/participations")
	public ResponseEntity<List<Participation>> findUserParticipations(@PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.findUserParticipations(id));
	}

	@PostMapping
	public ResponseEntity<AppUser> saveUser(@Valid @RequestBody AppUserInputDto appUserInputDto) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveUser(appUserInputDto));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AppUser> updateUser(@Valid @RequestBody AppUserInputDto appUserInputDto, @PathVariable Long id) {
		return ResponseEntity.ok().body(appUserService.updateUser(appUserInputDto, id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		appUserService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
