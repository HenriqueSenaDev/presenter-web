package gov.edu.anm.presenter.domain.participation;

import gov.edu.anm.presenter.api.common.requests.participations.AddJurorParticipationRequest;
import gov.edu.anm.presenter.api.common.requests.participations.AddSpectatorParticipationRequest;
import gov.edu.anm.presenter.api.common.dtos.participation.ParticipationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;

public interface ParticipationService {
    ParticipationOutputDto findById(Long userId, Long eventId);
    UserParticipationOutputDto addJurorParticipation(AddJurorParticipationRequest addJurorParticipationRequest);
    UserParticipationOutputDto addSpectatorParticipation(AddSpectatorParticipationRequest addSpectatorParticipationRequest);
    void removeParticipation(Long userId, Long eventId);
}
