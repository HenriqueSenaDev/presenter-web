package gov.edu.anm.presenter.api.team;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import gov.edu.anm.presenter.api.team.dtos.TeamCreateDto;
import gov.edu.anm.presenter.api.team.dtos.TeamUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import gov.edu.anm.presenter.api.appuser.AppUser;
import gov.edu.anm.presenter.api.avaliation.Avaliation;
import gov.edu.anm.presenter.api.avaliation.AvaliationPK;
import gov.edu.anm.presenter.api.appuser.AppUserRepository;
import gov.edu.anm.presenter.api.avaliation.AvaliationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final AppUserRepository appUserRepository;
    private final TeamRepository teamRepository;
    private final AvaliationRepository avaliationRepository;

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team not found"));
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Avaliation> findTeamAvaliations(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        return List.copyOf(team.getAvaliations());
    }
    
    @Override
    public Team saveTeam(TeamCreateDto teamCreateDto) {
        Team team = new Team(teamCreateDto);
        team.setAvaliations(new HashSet<>());
        team.setPresented(false);
        return teamRepository.save(team);
    }

    @Override
    public Avaliation addAvaliation(Long teamId, Long userId, Double value) {
        AppUser user = appUserRepository.findById(userId).get();
        Team team = teamRepository.findById(teamId).get();
        AvaliationPK pk = new AvaliationPK(user, team);
        return avaliationRepository.save(new Avaliation(pk, value));
    }

    @Override
    public Team updateTeam(TeamUpdateDto teamUpdateDto, Long id) {
        Team userTeam = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team not found"));

        if (teamUpdateDto.getName() != null) {
            userTeam.setName(teamUpdateDto.getName());
        }
        if (teamUpdateDto.getProject() != null) {
            userTeam.setProject(teamUpdateDto.getProject());
        }
        if (teamUpdateDto.getClassroom() != null) {
            userTeam.setClassroom(teamUpdateDto.getClassroom());
        }
        if (teamUpdateDto.getPresented() != null) {
            userTeam.setPresented(teamUpdateDto.getPresented());
        }
        if (teamUpdateDto.getMembers() != null) {
            userTeam.setMembers(teamUpdateDto.getMembers());
        }
        return teamRepository.saveAndFlush(userTeam);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void deleteAvaliation(Long teamId, Long userId) {
        Team team = teamRepository.getById(teamId);
        AppUser user = appUserRepository.getById(userId);
        Avaliation aval = team.getAvaliations().stream().filter(x -> x.getId().getUser().equals(user)).findFirst()
                .orElse(null);
        if (aval != null)
            avaliationRepository.delete(aval);
    }
}
