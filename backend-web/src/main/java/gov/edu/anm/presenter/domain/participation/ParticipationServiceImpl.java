package gov.edu.anm.presenter.domain.participation;

import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserRepository;
import gov.edu.anm.presenter.domain.event.Event;
import gov.edu.anm.presenter.domain.event.EventRepository;
import gov.edu.anm.presenter.domain.event.EventRole;
import gov.edu.anm.presenter.api.common.requests.participations.AddJurorParticipationRequest;
import gov.edu.anm.presenter.api.common.requests.participations.AddSpectatorParticipationRequest;
import gov.edu.anm.presenter.api.common.dtos.participation.ParticipationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;
import gov.edu.anm.presenter.domain.exceptions.UnmatchedCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserParticipationOutputDto addJurorParticipation(AddJurorParticipationRequest addJurorParticipationRequest) {
        AppUser user = appUserRepository.findById(addJurorParticipationRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findByJoinCode(addJurorParticipationRequest.getJoinCode())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        if (!event.getJurorCode().equals(addJurorParticipationRequest.getJurorCode()))
            throw new UnmatchedCodeException("Event juror code does not match");

        ParticipationPK id = new ParticipationPK(event, user);
        return new UserParticipationOutputDto(participationRepository.save(new Participation(id, EventRole.JUROR)));
    }

    @Override
    public UserParticipationOutputDto addSpectatorParticipation(AddSpectatorParticipationRequest addSpectatorParticipationRequest) {
        AppUser user = appUserRepository.findById(addSpectatorParticipationRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findByJoinCode(addSpectatorParticipationRequest.getJoinCode())
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
