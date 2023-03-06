package gov.edu.anm.presenter.domain.participation;

import gov.edu.anm.presenter.api.common.dtos.participation.ParticipationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;
import gov.edu.anm.presenter.domain.event.EventRole;

import java.util.List;

public interface ParticipationService {
    ParticipationOutputDto findById(Long userId, Long eventId);
    UserParticipationOutputDto addParticipation(Long userId, Long eventId, EventRole role);
    void removeParticipation(Long userId, Long eventId);
    List<ParticipationOutputDto> findAll();
}
