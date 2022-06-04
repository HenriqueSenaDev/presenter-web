package gov.edu.anm.presenter.services;

import java.util.List;

import gov.edu.anm.presenter.entities.Avaliation;
import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.entities.Participation;
import gov.edu.anm.presenter.entities.Team;

public interface EventService {
    // Event methods
    public Event findEventById(Long id);

    public Event findEventByName(String username);

    public List<Event> findAllEvents();

    public List<Participation> findEventParticipations(Long id);

    public List<Team> findEventTeams(Long id);

    public Event saveEvent(Event event);

    public Avaliation addAvaliation(Long teamId, Long userId, Double value);

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
