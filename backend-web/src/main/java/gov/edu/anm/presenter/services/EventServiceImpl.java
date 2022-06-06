package gov.edu.anm.presenter.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Avaliation;
import gov.edu.anm.presenter.entities.AvaliationPK;
import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.entities.Participation;
import gov.edu.anm.presenter.entities.ParticipationPK;
import gov.edu.anm.presenter.entities.Team;
import gov.edu.anm.presenter.repositories.AppUserRepository;
import gov.edu.anm.presenter.repositories.AvaliationRepository;
import gov.edu.anm.presenter.repositories.EventRepository;
import gov.edu.anm.presenter.repositories.EventRoleRepository;
import gov.edu.anm.presenter.repositories.ParticipationRepository;
import gov.edu.anm.presenter.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final EventRoleRepository eventRoleRepository;
    private final TeamRepository teamRepository;
    private final ParticipationRepository participationRepository;
    private final AvaliationRepository avaliationRepository;

    // Event methods
    @Override
    public Event findEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Event findEventByName(String username) {
        return eventRepository.findByName(username);
    }

    @Override
    public Event findEventByCode(Integer code) {
        return eventRepository.findByCode(code);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Participation> findEventParticipations(Long id) {
        Event evt = eventRepository.findById(id).get();
        return List.copyOf(evt.getParticipations());
    }

    @Override
    public List<Team> findEventTeams(Long id) {
        List<Participation> parts = findEventParticipations(id);
        List<Team> teams = new ArrayList<>();
        parts.forEach(part -> {
            teams.add(part.getTeam());
        });
        return teams;
    }

    @Override
    public Event saveEvent(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public Avaliation addAvaliation(Long teamId, Long userId, Double value) {
        AppUser user = appUserRepository.findById(userId).get();
        Team team = teamRepository.findById(teamId).get();
        AvaliationPK pk = new AvaliationPK(user, team);
        return avaliationRepository.save(new Avaliation(pk, value));
    }

    @Override
    public Participation addParticipation(Integer eventCode, Integer jurorCode, Long appUserId, Long teamId) {
        Event event = eventRepository.findByCode(eventCode);
        AppUser user = appUserRepository.findById(appUserId).orElseGet(null);
        ParticipationPK participationPK = new ParticipationPK(event, user);
        Participation participation = null;
        if (jurorCode.equals(event.getJurorCode())) {
            EventRole eventRole = eventRoleRepository.findByName("JUROR");
            participation = new Participation(participationPK, eventRole, null);
        } else {
            EventRole eventRole = eventRoleRepository.findByName("MEMBER");
            Team team = teamRepository.findById(teamId).orElseGet(null);
            participation = new Participation(participationPK, eventRole, team);
        }
        return participationRepository.save(participation);
    }

    @Override
    public Event updateEvent(Event event, Long id) {
        Event evt = eventRepository.findById(id).get();
        if (event.getCode() != null) {
            evt.setCode(event.getCode());
        }
        if (event.getName() != null) {
            evt.setName(event.getName());
        }
        if (event.getJurorCode() != null) {
            evt.setJurorCode(event.getJurorCode());
        }
        if (!event.getParticipations().isEmpty()) {
            evt.setParticipations(event.getParticipations());
        }
        return eventRepository.saveAndFlush(evt);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void deleteParticipation(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        Participation participation = event.getParticipations().stream().filter(
                x -> x.getId().getUser().getId() == userId).findFirst().orElse(null);
        participationRepository.delete(participation);
    }

    // EventRole methods
    @Override
    public EventRole findEventRoleById(Long id) {
        return eventRoleRepository.findById(id).get();
    }

    @Override
    public List<EventRole> findAllEventRoles() {
        return eventRoleRepository.findAll();
    }

    @Override
    public EventRole saveEventRole(EventRole eventRole) {
        return eventRoleRepository.save(eventRole);
    }

    @Override
    public EventRole updateEventRole(EventRole eventRole, Long id) {
        EventRole role = eventRoleRepository.getById(id);
        if (eventRole.getName() != null) {
            role.setName(eventRole.getName());
        }
        return eventRoleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteEventRole(Long id) {
        eventRoleRepository.deleteById(id);
    }
}
