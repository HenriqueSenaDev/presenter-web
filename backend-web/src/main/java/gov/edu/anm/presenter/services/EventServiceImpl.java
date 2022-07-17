package gov.edu.anm.presenter.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Avaliation;
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
   private final UserService userService;
   private final EventRepository eventRepository;
   private final EventRoleRepository eventRoleRepository;
   private final ParticipationRepository participationRepository;
   private final AvaliationRepository avaliationRepository;
   private final TeamRepository teamRepository;
   private final TeamService teamService;

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
      Set<Team> teams = new HashSet<>();
      parts.forEach(part -> {
         if (part.getTeam() != null) {
            teams.add(part.getTeam());
         }
      });
      teams.forEach(team -> {
         List<Avaliation> avaliations = teamService.findTeamAvaliations(team.getId());
         Optional<Double> ponctuation = avaliations.stream()
               .map(Avaliation::getValue)
               .reduce((n1, n2) -> n1 + n2);
         if (avaliations.size() == 0)
            team.setAverage(0.0);
         else
            team.setAverage(ponctuation.orElse(0.0) / avaliations.size());
         team.setAvaliationsQuantity(avaliations.size());
      });
      return List.copyOf(teams);
   }

   @Override
   public Event saveEvent(Event event) {
      eventRepository.save(event);
      return event;
   }

   @Override
   public Participation addMemberParticipation(Integer eventCode, Long appUserId, Long teamId) {
      Event event = findEventByCode(eventCode);
      AppUser user = appUserRepository.findById(appUserId).get();
      Team team = teamRepository.findById(teamId).get();
      ParticipationPK pk = new ParticipationPK(event, user);
      EventRole eventRole = eventRoleRepository.findByName("MEMBER");
      return participationRepository.save(new Participation(pk, eventRole, team));
   }

   @Override
   public Participation addJurorParticipation(Integer eventCode, Integer jurorCode, Long appUserId) {
      Event event = eventRepository.findByCode(eventCode);
      AppUser user = appUserRepository.findById(appUserId).orElseGet(null);
      ParticipationPK participationPK = new ParticipationPK(event, user);
      Participation participation = null;
      if (jurorCode.equals(event.getJurorCode())) {
         EventRole eventRole = eventRoleRepository.findByName("JUROR");
         participation = new Participation(participationPK, eventRole, null);
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
      if (event.getDescription() != null) {
         evt.setDescription(event.getDescription());
      }
      return eventRepository.saveAndFlush(evt);
   }

   // Método temporário
   @Override
   public List<Participation> updateMembersParticipations(List<String> usernames, Long teamId, Integer eventCode) {
      Team team = teamRepository.findById(teamId).get();
      List<Participation> parts = participationRepository.findAll();
      parts.removeIf(x -> x.getTeam() != team);

      if (usernames.isEmpty()) {
         participationRepository.deleteAll(parts);
      }

      List<AppUser> newTeamUsers = new ArrayList<>();
      usernames.forEach(username -> {
         AppUser searchUser = appUserRepository.findByUsername(username);
         if (searchUser == null) {
            searchUser = new AppUser(null, username, "testpassforstudent", new HashSet<>());
            searchUser = userService.saveUser(searchUser);
            userService.addRoleToUser(username, "ROLE_STUDENT");
            addMemberParticipation(eventCode, searchUser.getId(), teamId);
         } else {
            boolean x = false;
            for (Participation part : parts) {
               if (part.getId().getUser() == searchUser) {
                  x = true;
               }
            }
            if (!x) {
               addMemberParticipation(eventCode, searchUser.getId(), teamId);
            }
         }
         newTeamUsers.add(searchUser);
      });

      List<Participation> newCurrentParts = parts.stream()
            .filter(part -> newTeamUsers.contains(part.getId().getUser()))
            .collect(Collectors.toList());

      parts.removeIf(x -> newTeamUsers.contains(x.getId().getUser()));
      participationRepository.deleteAll(parts);

      return newCurrentParts;
   }

   @Override
   public void deleteEvent(Long id) {
      Event event = findEventById(id);
      List<Team> teams = findEventTeams(id);
      participationRepository.deleteAll(event.getParticipations());
      teams.forEach(team -> {
         avaliationRepository.deleteAll(team.getAvaliations());
      });
      teamRepository.deleteAll(teams);
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
