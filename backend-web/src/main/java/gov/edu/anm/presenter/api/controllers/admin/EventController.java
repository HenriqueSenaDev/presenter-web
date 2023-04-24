package gov.edu.anm.presenter.api.controllers.admin;

import gov.edu.anm.presenter.api.common.dtos.event.EventInputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamInputDto;
import gov.edu.anm.presenter.api.common.utils.ApiUtilities;
import gov.edu.anm.presenter.domain.event.Event;
import gov.edu.anm.presenter.domain.event.EventService;
import gov.edu.anm.presenter.domain.participation.Participation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventById(id));
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<Event> findByJoinCode(@PathVariable String code) {
        return ResponseEntity.ok().body(eventService.findEventByJoinCode(ApiUtilities.convertStringPath(code)));
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() {
        return ResponseEntity.ok().body(eventService.findAllEvents());
    }

    @GetMapping(value = "/{id}/participations")
    public ResponseEntity<List<Participation>> findEventParticipations(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventParticipations(id));
    }
    
    @PostMapping
    public ResponseEntity<Event> saveEvent(@Valid @RequestBody EventInputDto event) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());
        return ResponseEntity.created(uri).body(eventService.saveEvent(event));
    }

    @PostMapping(value = "/{eventId}/teams")
    public ResponseEntity<Event> createTeamInEvent(@Valid @RequestBody TeamInputDto team, @PathVariable Long eventId) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());
        return ResponseEntity.created(uri).body(eventService.createTeamInEvent(eventId, team));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Event> updateEvent(@Valid @RequestBody EventInputDto eventInputDto, @PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.updateEvent(eventInputDto, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
