package gov.edu.anm.presenter.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.entities.Participation;
import gov.edu.anm.presenter.entities.Team;
import gov.edu.anm.presenter.services.EventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/events")
@RequiredArgsConstructor
public class EventResource {
    private final EventService eventService;

    // Event methods
    @GetMapping(value = "/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventById(id));
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<Event> findByCode(@PathVariable Integer code) {
        return ResponseEntity.ok().body(eventService.findEventByCode(code));
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() {
        return ResponseEntity.ok().body(eventService.findAllEvents());
    }

    @GetMapping(value = "/participations/{id}")
    public ResponseEntity<List<Participation>> findEventParticipations(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventParticipations(id));
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<List<Team>> findEventTeams(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventTeams(id));
    }

    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
        return ResponseEntity.created(uri).body(eventService.saveEvent(event));
    }

    @PostMapping(value = "/participations/add/juror")
    public ResponseEntity<Participation> addJurorParticipation(
            @RequestParam Integer eventCode,
            @RequestParam Integer jurorCode,
            @RequestParam Long userId) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/participations").toUriString());
        return ResponseEntity.created(uri)
                .body(eventService.addJurorParticipation(eventCode, jurorCode, userId));
    }

    @PostMapping(value = "/participations/add/member")
    public ResponseEntity<Participation> addMemberParticipation(
            @RequestParam Integer eventCode,
            @RequestParam Long appUserId,
            @RequestParam Long teamId) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/participations").toUriString());
        return ResponseEntity.created(uri).body(eventService.addMemberParticipation(eventCode, appUserId, teamId));
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

    @DeleteMapping(value = "/participations")
    public ResponseEntity<?> deleteParticipation(
            @RequestParam Long userId,
            @RequestParam Long eventId) {
        eventService.deleteParticipation(userId, eventId);
        return ResponseEntity.ok("The participation has been deleted.");
    }

    // Event Roles methods
    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<EventRole> findEventRoleById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventRoleById(id));
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<List<EventRole>> findAllEventRoles() {
        return ResponseEntity.ok().body(eventService.findAllEventRoles());
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<EventRole> saveEventRole(@RequestBody EventRole eventRole) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/roles").toUriString());
        return ResponseEntity.created(uri).body(eventService.saveEventRole(eventRole));
    }

    @PutMapping(value = "/roles/{id}")
    public ResponseEntity<EventRole> updateEventRole(@PathVariable Long id, @RequestBody EventRole eventRole) {
        return ResponseEntity.ok().body(eventService.updateEventRole(eventRole, id));
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<?> deleteEventRole(@PathVariable Long id) {
        eventService.deleteEventRole(id);
        return ResponseEntity.ok().body("The EventRole has been deleted.");
    }

}
