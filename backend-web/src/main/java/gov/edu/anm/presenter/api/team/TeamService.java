package gov.edu.anm.presenter.api.team;

import java.util.List;

import gov.edu.anm.presenter.api.avaliation.dtos.TeamAvaliationOutputDto;
import gov.edu.anm.presenter.api.team.dtos.TeamOutputDto;
import gov.edu.anm.presenter.api.team.dtos.TeamUpdateDto;

public interface TeamService {
    TeamOutputDto findById(Long id);

    List<TeamOutputDto> findAll();

    List<TeamAvaliationOutputDto> findTeamAvaliations(Long id);

    Team updateTeam(TeamUpdateDto teamUpdateDto, Long id);

    void deleteTeam(Long id);
}
