package gov.edu.anm.presenter.api.participation;

import gov.edu.anm.presenter.api.participation.dtos.AddJurorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.UserParticipationOutputDto;

public interface ParticipationService {
    UserParticipationOutputDto addJurorParticipation(AddJurorRequestDto addJurorRequestDto);

    void removeParticipation(Long userId, Long eventId);
}
