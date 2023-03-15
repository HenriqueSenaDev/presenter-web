package gov.edu.anm.presenter.api.controllers.user;

import gov.edu.anm.presenter.api.common.dtos.event.EventOutputDto;
import gov.edu.anm.presenter.api.common.utils.ProfileUtilities;
import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserService;
import gov.edu.anm.presenter.domain.event.Event;
import gov.edu.anm.presenter.domain.event.EventService;
import gov.edu.anm.presenter.domain.exceptions.OutOfEventException;
import gov.edu.anm.presenter.domain.participation.Participation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/events")
@RequiredArgsConstructor
public class UserEventController {
    private final ProfileUtilities profileUtilities;
    private final AppUserService appUserService;
    private final EventService eventService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventOutputDto> findUserEvent(@PathVariable Long eventId) {
        var user = (AppUser) profileUtilities.getAuthenticatedUser();

        Participation part = appUserService.findUserParticipations(user.getId()).stream()
                .filter(x -> x.getId().getEvent().getId().equals(eventId)).findFirst()
                .orElseThrow(() -> new OutOfEventException("Not belonging to the event"));

        Event event = eventService.findEventById(part.getId().getEvent().getId());
        return ResponseEntity.ok(new EventOutputDto(event));
    }

}
