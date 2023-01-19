package gov.edu.anm.presenter.api.event;

import java.util.List;

import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.team.Team;

public interface EventService {
    // Event methods
    public Event findEventById(Long id);

    public Event findEventByName(String username);

    public Event findEventByCode(Integer code);

    public List<Event> findAllEvents();

    public List<Team> findEventTeams(Long id);

    public List<Participation> findEventParticipations(Long id);
    
    public Event saveEvent(Event event);

    public Participation addMemberParticipation(Integer eventCode, Long appUserId, Long teamId);

    public Participation addJurorParticipation(Integer eventCode, Integer jurorCode, Long appUserId);

    public Event updateEvent(Event event, Long id);

    public List<Participation> updateMembersParticipations(List<String> usernames, Long teamId, Integer eventCode);

    public void deleteEvent(Long id);

    public void deleteParticipation(Long userId, Long eventId);

    // EventRoles methods
    public EventRole findEventRoleById(Long id);

    public List<EventRole> findAllEventRoles();

    public EventRole saveEventRole(EventRole eventRole);

    public EventRole updateEventRole(EventRole eventRole, Long id);

    public void deleteEventRole(Long id);
}
