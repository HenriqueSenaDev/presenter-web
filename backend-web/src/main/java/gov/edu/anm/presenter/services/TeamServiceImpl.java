package gov.edu.anm.presenter.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
        return teamRepository.findById(id).get();
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
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
        if (team.getPonctuation() != null) {
            userTeam.setPonctuation(team.getPonctuation());
        }
        if (team.getAvaliations() != null) {
            userTeam.setAvaliations(team.getAvaliations());
        }
        if (team.getAverage() != null) {
            userTeam.setAverage(team.getAverage());
        }
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
