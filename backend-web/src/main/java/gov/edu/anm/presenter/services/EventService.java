package gov.edu.anm.presenter.services;

import java.util.List;

import gov.edu.anm.presenter.dto.ParticipationDTO;
import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.entities.Participation;

public interface EventService {
    // Event methods
    public Event findEventById(Long id);

    public Event findEventByName(String username);

    public List<Event> findAllEvents();

    public List<ParticipationDTO> findEventParticipations(Long id);

    public Event saveEvent(Event event);

    public Participation addParticipation(Long appUserId, Long eventId, Long eventRoleId, Long teamId);

    public Event updateEvent(Event event, Long id);

    public void deleteEvent(Long id);

    public void deleteParticipation(Long userId, Long eventId);

    // EventRoles methods
    public EventRole findEventRoleById(Long id);

    public List<EventRole> findAllEventRoles();

    public EventRole saveEventRole(EventRole eventRole);

    public EventRole updateEventRole(EventRole eventRole, Long id);

    public void deleteEventRole(Long id);
}
