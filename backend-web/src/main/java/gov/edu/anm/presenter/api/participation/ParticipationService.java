package gov.edu.anm.presenter.api.participation;

import gov.edu.anm.presenter.api.participation.dtos.AddJurorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.AddSpectatorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;
import gov.edu.anm.presenter.api.participation.dtos.UserParticipationOutputDto;

public interface ParticipationService {
    ParticipationOutputDto findById(Long userId, Long eventId);
    UserParticipationOutputDto addJurorParticipation(AddJurorRequestDto addJurorRequestDto);
    UserParticipationOutputDto addSpectatorParticipation(AddSpectatorRequestDto addSpectatorRequestDto);
    void removeParticipation(Long userId, Long eventId);
}
