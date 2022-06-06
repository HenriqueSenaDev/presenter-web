package gov.edu.anm.presenter.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import gov.edu.anm.presenter.entities.Avaliation;
import gov.edu.anm.presenter.entities.Team;
import gov.edu.anm.presenter.repositories.EventRepository;
import gov.edu.anm.presenter.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final EventRepository eventRepository;

    @Override
    public Team findById(Long id) {
        Team team = teamRepository.findById(id).get();
        List<Avaliation> avaliations = findTeamAvaliations(team.getId());
        Optional<Double> ponctuation = avaliations.stream()
                .map(Avaliation::getValue)
                .reduce((n1, n2) -> n1 + n2);
        team.setAverage(ponctuation.get() / avaliations.size());
        team.setAvaliationsQuantity(avaliations.size());
        return team;
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = teamRepository.findAll();
        teams.forEach(team -> {
            List<Avaliation> avaliations = findTeamAvaliations(team.getId());
            Optional<Double> ponctuation = avaliations.stream()
                    .map(Avaliation::getValue)
                    .reduce((n1, n2) -> n1 + n2);
            team.setAverage(ponctuation.get() / avaliations.size());
            team.setAvaliationsQuantity(avaliations.size());
        });
        return teams;
    }

    @Override
    public List<Avaliation> findTeamAvaliations(Long id) {
        return List.copyOf(teamRepository.findById(id).get().getAvaliations());
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team, Long id) {
        Team userTeam = teamRepository.getById(id);
        if (team.getName() != null) {
            userTeam.setName(team.getName());
        }
        if (team.getProject() != null) {
            userTeam.setProject(team.getProject());
        }
        if (team.getClassRoom() != null) {
            userTeam.setClassRoom(team.getClassRoom());
        }
        if (team.getAvaliations() != null) {
            userTeam.setAvaliations(team.getAvaliations());
        }
        // if (team.getAverage() != null) {
        // userTeam.setAverage(team.getAverage());
        // }
        if (team.getPresented() != null) {
            userTeam.setPresented(team.getPresented());
        }
        if (team.getEvent() != null) {
            userTeam.setEvent(eventRepository.getById(team.getEvent().getId()));
        }
        return teamRepository.saveAndFlush(userTeam);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
