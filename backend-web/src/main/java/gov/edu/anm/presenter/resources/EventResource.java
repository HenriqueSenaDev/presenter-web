package gov.edu.anm.presenter.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.services.EventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/events")
@RequiredArgsConstructor
public class EventResource {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        Event savedEvent = eventService.saveEvent(event);
        return ResponseEntity.ok().body(savedEvent);
    }

}
