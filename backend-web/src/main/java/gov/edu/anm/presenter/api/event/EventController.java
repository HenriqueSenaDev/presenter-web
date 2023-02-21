package gov.edu.anm.presenter.api.event;

import gov.edu.anm.presenter.api.event.dtos.EventCreateDto;
import gov.edu.anm.presenter.api.event.dtos.EventOutputDto;
import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.team.dtos.TeamCreateDto;
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
    public ResponseEntity<Event> findByJoinCode(@PathVariable String code) {
        return ResponseEntity.ok().body(eventService.findEventByJoinCode(code));
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() {
        return ResponseEntity.ok().body(eventService.findAllEvents());
    }

    @GetMapping(value = "/participations/{id}")
    public ResponseEntity<List<Participation>> findEventParticipations(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventParticipations(id));
    }
    
    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody EventCreateDto event) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
        return ResponseEntity.created(uri).body(eventService.saveEvent(event));
    }

    @PostMapping(value = "/{eventId}/teams")
    public ResponseEntity<Event> createTeamInEvent(@RequestBody TeamCreateDto team, @PathVariable Long eventId) {
        return ResponseEntity.ok().body(eventService.createTeamInEvent(eventId, team));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, @PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.updateEvent(event, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("The Event has been deleted.");
    }

}
