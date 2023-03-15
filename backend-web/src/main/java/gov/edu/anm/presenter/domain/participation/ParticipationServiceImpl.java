package gov.edu.anm.presenter.domain.participation;

import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserRepository;
import gov.edu.anm.presenter.domain.event.Event;
import gov.edu.anm.presenter.domain.event.EventRepository;
import gov.edu.anm.presenter.domain.event.EventRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public Participation findById(Long userId, Long eventId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        return participationRepository.findById(new ParticipationPK(event, user))
                .orElseThrow(() -> new EntityNotFoundException("Participation not found"));
    }

    @Override
    public List<Participation> findAll() {
        return participationRepository.findAll();
    }

    @Override
    public Participation addParticipation(Long userId, Long eventId, EventRole role) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        ParticipationPK id = new ParticipationPK(event, user);
        return participationRepository.save(new Participation(id, role));
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
