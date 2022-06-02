package gov.edu.anm.presenter.services;

import java.util.List;

import gov.edu.anm.presenter.entities.Team;

public interface TeamService {
    public Team findById(Long id);

    public List<Team> findAll();

    public Team saveTeam(Team team);

    public Team updateTeam(Team team, Long id);

    public void deleteTeam(Long id);
}
