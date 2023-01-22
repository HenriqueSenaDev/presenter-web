package gov.edu.anm.presenter.api.team;

import java.util.List;

import gov.edu.anm.presenter.avaliation.Avaliation;
public interface TeamService {
    Team findById(Long id);

    List<Team> findAll();

    List<Avaliation> findTeamAvaliations(Long id);
    
//    List<Team> findEventTeamsByQuery(String queryBy, String value, Long eventId);

    Team saveTeam(TeamInputDto team);

    Avaliation addAvaliation(Long teamId, Long userId, Double value);

    Team updateTeam(Team team, Long id);

    void deleteTeam(Long id);

    void deleteAvaliation(Long teamId, Long userId);
}
