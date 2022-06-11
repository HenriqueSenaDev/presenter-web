package gov.edu.anm.presenter.services;

import java.util.List;

import gov.edu.anm.presenter.entities.Avaliation;
import gov.edu.anm.presenter.entities.Team;

public interface TeamService {
    public Team findById(Long id);

    public List<Team> findAll();

    public List<Avaliation> findTeamAvaliations(Long id);

    public Team saveTeam(Team team);

    public Avaliation addAvaliation(Long teamId, Long userId, Double value);

    public Team updateTeam(Team team, Long id);

    public void deleteTeam(Long id);
}
