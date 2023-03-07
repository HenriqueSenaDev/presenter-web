package gov.edu.anm.presenter.api.user;

import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;
import gov.edu.anm.presenter.api.common.requests.participations.AddUserJurorParticipationRequest;
import gov.edu.anm.presenter.api.common.requests.participations.AddUserSpectatorParticipationRequest;
import gov.edu.anm.presenter.api.common.utils.ProfileUtilities;
import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.event.Event;
import gov.edu.anm.presenter.domain.event.EventRepository;
import gov.edu.anm.presenter.domain.event.EventRole;
import gov.edu.anm.presenter.domain.exceptions.UnmatchedCodeException;
import gov.edu.anm.presenter.domain.participation.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/user/participations")
@RequiredArgsConstructor
public class UserParticipationController {
    private final ProfileUtilities profileUtilities;
    private final ParticipationService participationService;
    private final EventRepository eventRepository;

    @PutMapping("/juror")
    public ResponseEntity<UserParticipationOutputDto> addJurorParticipation(@RequestBody AddUserJurorParticipationRequest request) {
        var user = (AppUser) profileUtilities.getAuthenticatedUser();
        Event event = eventRepository.findByJoinCode(request.getJoinCode())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        if (!request.getJurorCode().equals(event.getJurorCode()))
            throw new UnmatchedCodeException("Juror code doesn't match");

        return ResponseEntity.ok(participationService.addParticipation(user.getId(), event.getId(), EventRole.JUROR));
    }

    @PutMapping("/spectator")
    public ResponseEntity<UserParticipationOutputDto> addSpectatorParticipation(@RequestBody AddUserSpectatorParticipationRequest request) {
        var user = (AppUser) profileUtilities.getAuthenticatedUser();
        Event event = eventRepository.findByJoinCode(request.getJoinCode())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        return ResponseEntity.ok(participationService.addParticipation(user.getId(), event.getId(), EventRole.SPECTATOR));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> removeUserParticipation(@PathVariable Long eventId) {
        var user = (AppUser) profileUtilities.getAuthenticatedUser();
        participationService.removeParticipation(user.getId(), eventId);
        return ResponseEntity.noContent().build();
    }

}