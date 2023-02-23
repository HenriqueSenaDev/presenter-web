package gov.edu.anm.presenter.api.avaliation;

import gov.edu.anm.presenter.api.appuser.AppUser;
import gov.edu.anm.presenter.api.appuser.AppUserRepository;
import gov.edu.anm.presenter.api.avaliation.dtos.AddAvaliationRequestDto;
import gov.edu.anm.presenter.api.avaliation.dtos.AvaliationOutputDto;
import gov.edu.anm.presenter.api.team.Team;
import gov.edu.anm.presenter.api.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AvaliationServiceImpl implements AvaliationService {
    private final AppUserRepository appUserRepository;
    private final TeamRepository teamRepository;
    private final AvaliationRepository avaliationRepository;

    @Override
    public AvaliationOutputDto findById(Long userId, Long teamId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team not found"));

        Avaliation avaliation = avaliationRepository.findById(new AvaliationPK(user, team))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avaliation not found"));

        return new AvaliationOutputDto(avaliation);
    }

    @Override
    public List<AvaliationOutputDto> findAll() {
        return avaliationRepository.findAll().stream().map(AvaliationOutputDto::new).toList();
    }

    @Override
    public AvaliationOutputDto addAvaliationToTeam(AddAvaliationRequestDto addAvaliationRequestDto) {
        AppUser user = appUserRepository.findById(addAvaliationRequestDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        Team team = teamRepository.findById(addAvaliationRequestDto.getTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team not found"));

        AvaliationPK id = new AvaliationPK(user, team);
        return new AvaliationOutputDto(avaliationRepository.save(new Avaliation(id, addAvaliationRequestDto.getValue())));
    }

    @Override
    public void deleteAvaliation(Long userId, Long teamId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team not found"));

        AvaliationPK id = new AvaliationPK(user, team);
        avaliationRepository.deleteById(id);
    }

}
