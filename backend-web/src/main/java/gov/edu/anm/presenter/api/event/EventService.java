package gov.edu.anm.presenter.api.event;

import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.team.TeamInputDto;

import java.util.List;

public interface EventService {

    Event findEventById(Long id);

    Event findEventByJoinCode(String code);

    List<Event> findAllEvents();

    List<Participation> findEventParticipations(Long id);
    
    Event saveEvent(EventInputDto event);

    Event updateEvent(Event event, Long id);

    Event putTeamInEvent(Long eventId, TeamInputDto team);

    void deleteEvent(Long id);

}
