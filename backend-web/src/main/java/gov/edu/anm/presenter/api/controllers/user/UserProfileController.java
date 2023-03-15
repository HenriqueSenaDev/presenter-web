package gov.edu.anm.presenter.api.controllers.user;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;
import gov.edu.anm.presenter.api.common.utils.ProfileUtilities;
import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserService;
import gov.edu.anm.presenter.domain.participation.Participation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user/profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final ProfileUtilities profileUtilities;
    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<AppUserOutputDto> getProfile() {
        var user = (AppUser) profileUtilities.getAuthenticatedUser();
        return ResponseEntity.ok(new AppUserOutputDto(user));
    }

    @GetMapping("/participations")
    public ResponseEntity<List<UserParticipationOutputDto>> getUserParticipations() {
        var user = (AppUser) profileUtilities.getAuthenticatedUser();
        List<Participation> parts = appUserService.findUserParticipations(user.getId());
        return ResponseEntity.ok(parts.stream().map(UserParticipationOutputDto::new).toList());
    }

}
