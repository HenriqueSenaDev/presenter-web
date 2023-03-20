package gov.edu.anm.presenter.api.controllers.user;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.api.common.utils.ProfileUtilities;
import gov.edu.anm.presenter.domain.appuser.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final ProfileUtilities profileUtilities;

    @GetMapping
    public ResponseEntity<AppUserOutputDto> getProfile() {
        var user = (AppUser) profileUtilities.getAuthenticatedUser();
        return ResponseEntity.ok(new AppUserOutputDto(user));
    }

}
