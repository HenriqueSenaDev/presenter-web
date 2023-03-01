package gov.edu.anm.presenter.api.admin;

import gov.edu.anm.presenter.api.common.dtos.event.EventInputDto;
import gov.edu.anm.presenter.api.common.dtos.event.EventOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.EventParticipationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamInputDto;
import gov.edu.anm.presenter.domain.event.Event;
import gov.edu.anm.presenter.domain.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventOutputDto> findEventById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventById(id));
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<EventOutputDto> findByJoinCode(@PathVariable String code) {
        return ResponseEntity.ok().body(eventService.findEventByJoinCode(code));
    }

    @GetMapping
    public ResponseEntity<List<EventOutputDto>> findAllEvents() {
        return ResponseEntity.ok().body(eventService.findAllEvents());
    }

    @GetMapping(value = "/participations/{id}")
    public ResponseEntity<List<EventParticipationOutputDto>> findEventParticipations(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventParticipations(id));
    }
    
    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody EventInputDto event) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
        return ResponseEntity.created(uri).body(eventService.saveEvent(event));
    }

    @PostMapping(value = "/{eventId}/teams")
    public ResponseEntity<EventOutputDto> createTeamInEvent(@RequestBody TeamInputDto team, @PathVariable Long eventId) {
        return ResponseEntity.ok().body(eventService.createTeamInEvent(eventId, team));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody EventInputDto eventInputDto, @PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.updateEvent(eventInputDto, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
