package gov.edu.anm.presenter.api.participation;

import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;

public interface ParticipationService {
    ParticipationOutputDto addJurorParticipation(Long userId, Long eventId);
}
