package gov.edu.anm.presenter.api.event;

import gov.edu.anm.presenter.api.event.dtos.EventInputDto;
import gov.edu.anm.presenter.api.event.dtos.EventOutputDto;
import gov.edu.anm.presenter.api.participation.dtos.EventParticipationOutputDto;
import gov.edu.anm.presenter.api.team.dtos.TeamInputDto;

import java.util.List;

public interface EventService {

    EventOutputDto findEventById(Long id);

    EventOutputDto findEventByJoinCode(String code);

    List<EventOutputDto> findAllEvents();

    List<EventParticipationOutputDto> findEventParticipations(Long id);
    
    Event saveEvent(EventInputDto event);

    Event updateEvent(EventInputDto event, Long id);

    EventOutputDto createTeamInEvent(Long eventId, TeamInputDto team);

    void deleteEvent(Long id);

}
