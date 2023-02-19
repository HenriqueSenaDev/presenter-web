package gov.edu.anm.presenter.api.participation;

import gov.edu.anm.presenter.api.appuser.AppUser;
import gov.edu.anm.presenter.api.appuser.AppUserRepository;
import gov.edu.anm.presenter.api.event.Event;
import gov.edu.anm.presenter.api.event.EventRepository;
import gov.edu.anm.presenter.api.event.EventRole;
import gov.edu.anm.presenter.api.participation.dtos.AddJurorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public ParticipationOutputDto addJurorParticipation(AddJurorRequestDto addJurorRequestDto) {
        AppUser user = appUserRepository.findById(addJurorRequestDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        Event event = eventRepository.findByJoinCode(addJurorRequestDto.getJoinCode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));

        if (!event.getJurorCode().equals(addJurorRequestDto.getJurorCode()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event juror code does not match");

        ParticipationPK id = new ParticipationPK(event, user);
        return new ParticipationOutputDto(participationRepository.save(new Participation(id, EventRole.JUROR)));
    }
}
