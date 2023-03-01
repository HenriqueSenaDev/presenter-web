package gov.edu.anm.presenter.domain.avaliations;

import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserRepository;
import gov.edu.anm.presenter.api.common.requests.AddAvaliationRequestDto;
import gov.edu.anm.presenter.api.common.dtos.avaliation.AvaliationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.avaliation.TeamAvaliationOutputDto;
import gov.edu.anm.presenter.domain.event.EventRole;
import gov.edu.anm.presenter.domain.participation.Participation;
import gov.edu.anm.presenter.domain.participation.ParticipationPK;
import gov.edu.anm.presenter.domain.participation.ParticipationRepository;
import gov.edu.anm.presenter.domain.team.Team;
import gov.edu.anm.presenter.domain.team.TeamRepository;
import gov.edu.anm.presenter.domain.exceptions.UnauthorizedRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AvaliationServiceImpl implements AvaliationService {
    private final AppUserRepository appUserRepository;
    private final TeamRepository teamRepository;
    private final AvaliationRepository avaliationRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public AvaliationOutputDto findById(Long userId, Long teamId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        Avaliation avaliation = avaliationRepository.findById(new AvaliationPK(user, team))
                .orElseThrow(() -> new EntityNotFoundException("Avaliation not found"));
        return new AvaliationOutputDto(avaliation);
    }

    @Override
    public List<AvaliationOutputDto> findAll() {
        return avaliationRepository.findAll().stream().map(AvaliationOutputDto::new).toList();
    }

    @Override
    public TeamAvaliationOutputDto addAvaliationToTeam(AddAvaliationRequestDto addAvaliationRequestDto) {
        AppUser user = appUserRepository.findById(addAvaliationRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Team team = teamRepository.findById(addAvaliationRequestDto.getTeamId())
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        Participation part = participationRepository.findById(new ParticipationPK(team.getEvent(), user))
                .orElseThrow(() -> new EntityNotFoundException("Participation not found"));
        if (part.getEventRole().equals(EventRole.SPECTATOR))
            throw new UnauthorizedRoleException("Not an event juror");

        AvaliationPK id = new AvaliationPK(user, team);
        return new TeamAvaliationOutputDto(avaliationRepository.save(new Avaliation(id, addAvaliationRequestDto.getValue())));
    }

    @Override
    public void deleteAvaliation(Long userId, Long teamId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        AvaliationPK id = new AvaliationPK(user, team);
        avaliationRepository.deleteById(id);
    }

}
