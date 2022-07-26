package gov.edu.anm.presenter.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Avaliation;
import gov.edu.anm.presenter.entities.AvaliationPK;
import gov.edu.anm.presenter.entities.Participation;
import gov.edu.anm.presenter.entities.Team;
import gov.edu.anm.presenter.repositories.AppUserRepository;
import gov.edu.anm.presenter.repositories.AvaliationRepository;
import gov.edu.anm.presenter.repositories.ParticipationRepository;
import gov.edu.anm.presenter.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final AppUserRepository appUserRepository;
    private final TeamRepository teamRepository;
    private final AvaliationRepository avaliationRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public Team findById(Long id) {
        Team team = teamRepository.findById(id).get();
        List<Avaliation> avaliations = findTeamAvaliations(team.getId());
        Optional<Double> ponctuation = avaliations.stream()
                .map(Avaliation::getValue)
                .reduce((n1, n2) -> n1 + n2);
        if (ponctuation.isEmpty()) {
            team.setAverage(0.0);
        } else {
            team.setAverage(ponctuation.orElse(0.0) / avaliations.size());
        }
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
            if (avaliations.size() == 0)
                team.setAverage(0.0);
            else
                team.setAverage(ponctuation.orElse(0.0) / avaliations.size());
            team.setAvaliationsQuantity(avaliations.size());
        });
        return teams;
    }

    @Override
    public List<String> findTeamMembersUsernames(Long id) {
        Team team = teamRepository.findById(id).get();
        List<Participation> parts = participationRepository.findAll();
        Set<String> membersUsernames = new HashSet<>();
        parts.removeIf(part -> part.getTeam() != team);
        parts.forEach(part -> {
            membersUsernames.add(part.getId().getUser().getUsername());
        });

        return List.copyOf(membersUsernames);
    }

    @Override
    public List<Avaliation> findTeamAvaliations(Long id) {
        return List.copyOf(teamRepository.findById(id).get().getAvaliations());
    }

    @Override
	public List<Team> findEventTeamsByName(String teamName, Long eventId) {
    	teamName = teamName.replace("+", " ");
		List<Team> teams = teamRepository.findEventTeamsByName(teamName, eventId);
		teams.forEach(team -> {
			List<Avaliation> avaliations = findTeamAvaliations(team.getId());
			Optional<Double> ponctuation = avaliations.stream().map(Avaliation::getValue).reduce((n1, n2) -> n1 + n2);
			if (avaliations.size() == 0)
				team.setAverage(0.0);
			else
				team.setAverage(ponctuation.orElse(0.0) / avaliations.size());
			team.setAvaliationsQuantity(avaliations.size());
		});
		return teams;
	}
    
    @Override
    public Team saveTeam(Team team) {
        team.setAvaliationsQuantity(0);
        team.setAvaliations(new HashSet<>());
        team.setAverage(0.0);
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
        if (team.getClassRoom() != null) {
            userTeam.setClassRoom(team.getClassRoom());
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
        Team team = teamRepository.getById(id);
        avaliationRepository.deleteAll(team.getAvaliations());
        List<Participation> parts = participationRepository.findAll();
        parts.removeIf(part -> part.getTeam() != team);
        participationRepository.deleteAll(parts);
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
