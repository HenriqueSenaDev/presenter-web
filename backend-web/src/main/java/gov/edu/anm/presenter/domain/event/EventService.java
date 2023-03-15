package gov.edu.anm.presenter.domain.event;

import gov.edu.anm.presenter.api.common.dtos.event.EventInputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamInputDto;
import gov.edu.anm.presenter.domain.participation.Participation;

import java.util.List;

public interface EventService {

    Event findEventById(Long id);

    Event findEventByJoinCode(String code);

    List<Event> findAllEvents();

    List<Participation> findEventParticipations(Long id);
    
    Event saveEvent(EventInputDto event);

    Event updateEvent(EventInputDto event, Long id);

    Event createTeamInEvent(Long eventId, TeamInputDto team);

    void deleteEvent(Long id);

}
