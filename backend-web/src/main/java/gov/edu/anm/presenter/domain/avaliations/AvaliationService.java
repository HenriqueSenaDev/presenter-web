package gov.edu.anm.presenter.domain.avaliations;

import gov.edu.anm.presenter.api.common.requests.avaliations.AddAvaliationRequest;
import gov.edu.anm.presenter.api.common.dtos.avaliation.AvaliationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.avaliation.TeamAvaliationOutputDto;

import java.util.List;

public interface AvaliationService {
    AvaliationOutputDto findById(Long userId, Long teamId);
    List<AvaliationOutputDto> findAll();
    TeamAvaliationOutputDto addAvaliationToTeam(Long userId,Long teamId, Double value);
    void deleteAvaliation(Long userId, Long teamId);

}
