package gov.edu.anm.presenter.api.team;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import gov.edu.anm.presenter.api.appuser.AppUser;
import gov.edu.anm.presenter.avaliation.Avaliation;
import gov.edu.anm.presenter.avaliation.AvaliationPK;
import gov.edu.anm.presenter.api.appuser.AppUserRepository;
import gov.edu.anm.presenter.avaliation.AvaliationRepository;
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

//    @Override
//	public List<Team> findEventTeamsByQuery(String queryBy, String value, Long eventId) {
//    	value = value.replace("+", " ");
//		List<Team> teams = EventTeamsQuery.valueOf(queryBy.toUpperCase())
//				.query(value, eventId, teamRepository);
//
//		teams.forEach(team -> {
//			List<Avaliation> avaliations = findTeamAvaliations(team.getId());
//			Optional<Double> ponctuation = avaliations.stream().map(Avaliation::getValue).reduce((n1, n2) -> n1 + n2);
//			if (avaliations.size() == 0)
//				team.setAverage(0.0);
//			else
//				team.setAverage(ponctuation.orElse(0.0) / avaliations.size());
//			team.setAvaliationsQuantity(avaliations.size());
//		});
//
//		return teams;
//	}
    
    @Override
    public Team saveTeam(TeamInputDto teamInputDto) {
        Team team = new Team(teamInputDto);
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
    public Team updateTeam(Team team, Long id) {
        Team userTeam = teamRepository.getById(id);
        if (team.getName() != null) {
            userTeam.setName(team.getName());
        }
        if (team.getProject() != null) {
            userTeam.setProject(team.getProject());
        }
        if (team.getClassroom() != null) {
            userTeam.setClassroom(team.getClassroom());
        }
        if (team.getAvaliations() != null) {
            userTeam.setAvaliations(team.getAvaliations());
        }
        if (team.getPresented() != null) {
            userTeam.setPresented(team.getPresented());
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
