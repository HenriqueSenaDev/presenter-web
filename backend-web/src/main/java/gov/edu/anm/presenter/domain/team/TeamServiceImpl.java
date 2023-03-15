package gov.edu.anm.presenter.domain.team;

import gov.edu.anm.presenter.api.common.dtos.team.TeamUpdateDto;
import gov.edu.anm.presenter.domain.avaliations.Avaliation;
import gov.edu.anm.presenter.domain.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Avaliation> findTeamAvaliations(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return List.copyOf(team.getAvaliations());
    }

    @Override
    public Event findTeamEvent(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        return team.getEvent();
    }

    @Override
    public Team updateTeam(TeamUpdateDto teamUpdateDto, Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        team.update(teamUpdateDto);
        return teamRepository.saveAndFlush(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
