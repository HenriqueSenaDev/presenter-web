package gov.edu.anm.presenter.api.event;

import gov.edu.anm.presenter.api.event.dtos.EventCreateDto;
import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.team.dtos.TeamCreateDto;

import java.util.List;

public interface EventService {

    Event findEventById(Long id);

    Event findEventByJoinCode(String code);

    List<Event> findAllEvents();

    List<Participation> findEventParticipations(Long id);
    
    Event saveEvent(EventCreateDto event);

    Event updateEvent(Event event, Long id);

    Event createTeamInEvent(Long eventId, TeamCreateDto team);

    void deleteEvent(Long id);

}
