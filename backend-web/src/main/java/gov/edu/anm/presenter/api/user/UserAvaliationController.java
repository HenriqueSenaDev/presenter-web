package gov.edu.anm.presenter.api.user;

import gov.edu.anm.presenter.api.common.dtos.avaliation.TeamAvaliationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;
import gov.edu.anm.presenter.api.common.requests.avaliations.AddUserAvaliationRequest;
import gov.edu.anm.presenter.api.common.utils.ProfileUtilities;
import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserService;
import gov.edu.anm.presenter.domain.avaliations.AvaliationService;
import gov.edu.anm.presenter.domain.event.Event;
import gov.edu.anm.presenter.domain.exceptions.OutOfEventException;
import gov.edu.anm.presenter.domain.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user/avaliations")
@RequiredArgsConstructor
public class UserAvaliationController {
    private final ProfileUtilities profileUtilities;
    private final AppUserService appUserService;
    private final AvaliationService avaliationService;
    private final TeamService teamService;

    @PutMapping
    public ResponseEntity<TeamAvaliationOutputDto> avaliateTeam(@RequestBody AddUserAvaliationRequest request) {
        var user = (AppUser) profileUtilities.getAuthenticatedUser();
        Event event = teamService.findTeamEvent(request.getTeamId());
        List<UserParticipationOutputDto> userParticipations = appUserService.findUserParticipations(user.getId());

        if (userParticipations.stream().noneMatch(x -> x.getEvent().getId().equals(event.getId())))
            throw new OutOfEventException("Not belonging to the event");

        return ResponseEntity.ok(avaliationService.addAvaliationToTeam(user.getId(), request.getTeamId(), request.getValue()));
    }
}
