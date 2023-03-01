package gov.edu.anm.presenter.domain.event;

import gov.edu.anm.presenter.api.common.dtos.event.EventInputDto;
import gov.edu.anm.presenter.api.common.dtos.event.EventOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.EventParticipationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamInputDto;

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
