package gov.edu.anm.presenter.domain.participation;

import gov.edu.anm.presenter.api.common.requests.AddJurorRequestDto;
import gov.edu.anm.presenter.api.common.requests.AddSpectatorRequestDto;
import gov.edu.anm.presenter.api.common.dtos.participation.ParticipationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;

public interface ParticipationService {
    ParticipationOutputDto findById(Long userId, Long eventId);
    UserParticipationOutputDto addJurorParticipation(AddJurorRequestDto addJurorRequestDto);
    UserParticipationOutputDto addSpectatorParticipation(AddSpectatorRequestDto addSpectatorRequestDto);
    void removeParticipation(Long userId, Long eventId);
}
