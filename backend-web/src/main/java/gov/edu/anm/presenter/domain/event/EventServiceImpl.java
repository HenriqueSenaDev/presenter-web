package gov.edu.anm.presenter.domain.event;

import gov.edu.anm.presenter.api.common.dtos.event.EventInputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamInputDto;
import gov.edu.anm.presenter.domain.participation.Participation;
import gov.edu.anm.presenter.domain.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {
	private final EventRepository eventRepository;

	@Override
	public Event findEventById(Long id) {
		return eventRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));
	}

	@Override
	public Event findEventByJoinCode(String code) {
		return eventRepository.findByJoinCode(code)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));
	}

	@Override
	public List<Event> findAllEvents() {
		return eventRepository.findAll();
	}

	@Override
	public List<Participation> findEventParticipations(Long id) {
		Event evt = eventRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));
		return List.copyOf(evt.getParticipations());
	}

	@Override
	public Event saveEvent(EventInputDto eventInputDto) {
		return eventRepository.save(new Event(eventInputDto));
	}

	@Override
	public Event createTeamInEvent(Long eventId, TeamInputDto teamInputDto) {
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));

		Team team = new Team(teamInputDto);
		team.setEvent(event);
		event.putTeam(team);

		return eventRepository.save(event);
	}

	@Override
	public Event updateEvent(EventInputDto eventInputDto, Long id) {
		eventRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));

		Event eventToUpdate = new Event(eventInputDto);
		eventToUpdate.setId(id);
		return eventRepository.saveAndFlush(eventToUpdate);
	}

	@Override
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

}
