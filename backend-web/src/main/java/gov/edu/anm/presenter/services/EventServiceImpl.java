package gov.edu.anm.presenter.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.entities.Participation;
import gov.edu.anm.presenter.entities.ParticipationPK;
import gov.edu.anm.presenter.repositories.AppUserRepository;
import gov.edu.anm.presenter.repositories.EventRepository;
import gov.edu.anm.presenter.repositories.EventRoleRepository;
import gov.edu.anm.presenter.repositories.ParticipationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final EventRoleRepository eventRoleRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public Event saveEvent(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public EventRole saveEventRole(EventRole eventRole) {
        EventRole role = eventRoleRepository.save(eventRole);
        return role;
    }

    @Override
    public Participation addParticipation(Long appUserId, Long eventId, Long eventRoleId, Long teamId) {
        AppUser user = appUserRepository.findById(appUserId).get();
        Event event = eventRepository.findById(eventId).get();
        ParticipationPK participationPK = new ParticipationPK(event, user);
        EventRole eventRole = eventRoleRepository.findById(eventRoleId).get();
        Participation participation = new Participation(participationPK, eventRole, null);
        participationRepository.save(participation);
        return participation;
    }

}
