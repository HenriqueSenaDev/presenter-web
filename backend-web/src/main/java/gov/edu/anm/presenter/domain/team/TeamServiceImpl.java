package gov.edu.anm.presenter.domain.team;

import gov.edu.anm.presenter.api.common.dtos.avaliation.TeamAvaliationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamOutputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamUpdateDto;
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
    public TeamOutputDto findById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        return new TeamOutputDto(team);
    }

    @Override
    public List<TeamOutputDto> findAll() {
        return teamRepository.findAll().stream().map(TeamOutputDto::new).toList();
    }

    @Override
    public List<TeamAvaliationOutputDto> findTeamAvaliations(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return team.getAvaliations().stream().map(TeamAvaliationOutputDto::new).toList();
    }

    @Override
    public Team updateTeam(TeamUpdateDto teamUpdateDto, Long id) {
        teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        Team teamToUpdate = new Team(teamUpdateDto);
        teamToUpdate.setId(id);
        return teamRepository.saveAndFlush(teamToUpdate);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
