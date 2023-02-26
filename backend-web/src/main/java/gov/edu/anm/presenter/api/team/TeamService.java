package gov.edu.anm.presenter.api.team;

import java.util.List;

import gov.edu.anm.presenter.api.team.dtos.TeamOutputDto;
import gov.edu.anm.presenter.api.team.dtos.TeamUpdateDto;
import gov.edu.anm.presenter.api.avaliation.Avaliation;
public interface TeamService {
    TeamOutputDto findById(Long id);

    List<TeamOutputDto> findAll();

    List<Avaliation> findTeamAvaliations(Long id);

    Team updateTeam(TeamUpdateDto teamUpdateDto, Long id);

    void deleteTeam(Long id);
}
