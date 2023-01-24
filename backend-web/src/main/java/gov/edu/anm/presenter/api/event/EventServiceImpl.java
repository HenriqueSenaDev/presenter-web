package gov.edu.anm.presenter.api.event;

import gov.edu.anm.presenter.api.event.dtos.EventCreateDto;
import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.team.Team;
import gov.edu.anm.presenter.api.team.dtos.TeamCreateDto;
import gov.edu.anm.presenter.api.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {
	private final EventRepository eventRepository;
	private final TeamRepository teamRepository;

	@Override
	public Event findEventById(Long id) {
		return eventRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));
	}

	@Override
	public Event findEventByJoinCode(String code) {
		return eventRepository.findByJoinCode(code)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));
	}

	@Override
	public List<Event> findAllEvents() {
		return eventRepository.findAll();
	}

	@Override
	public List<Participation> findEventParticipations(Long id) {
		Event evt = eventRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));
		return List.copyOf(evt.getParticipations());
	}

	@Override
	public Event saveEvent(EventCreateDto eventCreateDto) {
		return eventRepository.save(new Event(eventCreateDto));
	}

	@Override
	public Event putTeamInEvent(Long eventId, TeamCreateDto teamCreateDto) {
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));

		Team team = new Team(teamCreateDto);
		if (teamCreateDto.getId() != null) {
			team = teamRepository.findById(teamCreateDto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team not found"));
		}

		team.setEvent(event);
		event.putTeam(team);
		return eventRepository.save(event);
	}

	@Override
	public Event updateEvent(Event event, Long id) {
		Event evt = eventRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));

		if (event.getJoinCode() != null) {
			evt.setJoinCode(event.getJoinCode());
		}
		if (event.getName() != null) {
			evt.setName(event.getName());
		}
		if (event.getJurorCode() != null) {
			evt.setJurorCode(event.getJurorCode());
		}
		if (event.getDescription() != null) {
			evt.setDescription(event.getDescription());
		}

		return eventRepository.saveAndFlush(evt);
	}

	@Override
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

}
