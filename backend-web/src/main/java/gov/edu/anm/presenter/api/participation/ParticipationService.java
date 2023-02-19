package gov.edu.anm.presenter.api.participation;

import gov.edu.anm.presenter.api.participation.dtos.AddJurorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;

public interface ParticipationService {
    ParticipationOutputDto addJurorParticipation(AddJurorRequestDto addJurorRequestDto);
}
