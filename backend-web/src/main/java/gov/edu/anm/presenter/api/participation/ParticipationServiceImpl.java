package gov.edu.anm.presenter.api.participation;

import gov.edu.anm.presenter.api.appuser.AppUser;
import gov.edu.anm.presenter.api.appuser.AppUserRepository;
import gov.edu.anm.presenter.api.event.Event;
import gov.edu.anm.presenter.api.event.EventRepository;
import gov.edu.anm.presenter.api.event.EventRole;
import gov.edu.anm.presenter.api.participation.dtos.AddJurorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.AddSpectatorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;
import gov.edu.anm.presenter.api.participation.dtos.UserParticipationOutputDto;
import gov.edu.anm.presenter.exceptions.UnmatchedCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public ParticipationOutputDto findById(Long userId, Long eventId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        Participation participation = participationRepository.findById(new ParticipationPK(event, user))
                .orElseThrow(() -> new EntityNotFoundException("Participation not found"));
        return new ParticipationOutputDto(participation);
    }

    @Override
    public UserParticipationOutputDto addJurorParticipation(AddJurorRequestDto addJurorRequestDto) {
        AppUser user = appUserRepository.findById(addJurorRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findByJoinCode(addJurorRequestDto.getJoinCode())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        if (!event.getJurorCode().equals(addJurorRequestDto.getJurorCode()))
            throw new UnmatchedCodeException("Event juror code does not match");

        ParticipationPK id = new ParticipationPK(event, user);
        return new UserParticipationOutputDto(participationRepository.save(new Participation(id, EventRole.JUROR)));
    }

    @Override
    public UserParticipationOutputDto addSpectatorParticipation(AddSpectatorRequestDto addSpectatorRequestDto) {
        AppUser user = appUserRepository.findById(addSpectatorRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findByJoinCode(addSpectatorRequestDto.getJoinCode())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        ParticipationPK id = new ParticipationPK(event, user);
        return new UserParticipationOutputDto(participationRepository.save(new Participation(id, EventRole.SPECTATOR)));
    }

    @Override
    public void removeParticipation(Long userId, Long eventId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        ParticipationPK id = new ParticipationPK(event, user);
        participationRepository.deleteById(id);
    }
}
