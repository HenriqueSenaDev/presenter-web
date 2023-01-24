package gov.edu.anm.presenter.api.team;

import java.util.List;

import gov.edu.anm.presenter.api.team.dtos.TeamUpdateDto;
import gov.edu.anm.presenter.api.team.dtos.TeamCreateDto;
import gov.edu.anm.presenter.api.avaliation.Avaliation;
public interface TeamService {
    Team findById(Long id);

    List<Team> findAll();

    List<Avaliation> findTeamAvaliations(Long id);
    
//    List<Team> findEventTeamsByQuery(String queryBy, String value, Long eventId);

    Team saveTeam(TeamCreateDto team);

    Avaliation addAvaliation(Long teamId, Long userId, Double value);

    Team updateTeam(TeamUpdateDto teamUpdateDto, Long id);

    void deleteTeam(Long id);

    void deleteAvaliation(Long teamId, Long userId);
}
