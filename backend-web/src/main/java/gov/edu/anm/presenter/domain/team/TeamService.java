package gov.edu.anm.presenter.domain.team;

import java.util.List;

import gov.edu.anm.presenter.api.common.dtos.avaliation.TeamAvaliationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamOutputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamUpdateDto;

public interface TeamService {
    TeamOutputDto findById(Long id);

    List<TeamOutputDto> findAll();

    List<TeamAvaliationOutputDto> findTeamAvaliations(Long id);

    Team updateTeam(TeamUpdateDto teamUpdateDto, Long id);

    void deleteTeam(Long id);
}
