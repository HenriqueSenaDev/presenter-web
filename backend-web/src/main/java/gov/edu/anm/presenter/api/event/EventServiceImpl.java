package gov.edu.anm.presenter.api.event;

import gov.edu.anm.presenter.api.event.dtos.EventInputDto;
import gov.edu.anm.presenter.api.event.dtos.EventOutputDto;
import gov.edu.anm.presenter.api.participation.dtos.EventParticipationOutputDto;
import gov.edu.anm.presenter.api.team.Team;
import gov.edu.anm.presenter.api.team.dtos.TeamInputDto;
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
	public EventOutputDto findEventById(Long id) {
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));
		return new EventOutputDto(event);
	}

	@Override
	public EventOutputDto findEventByJoinCode(String code) {
		Event event = eventRepository.findByJoinCode(code)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));
		return new EventOutputDto(event);
	}

	@Override
	public List<EventOutputDto> findAllEvents() {
		return eventRepository.findAll().stream().map(EventOutputDto::new).toList();
	}

	@Override
	public List<EventParticipationOutputDto> findEventParticipations(Long id) {
		Event evt = eventRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));
		return evt.getParticipations().stream().map(EventParticipationOutputDto::new).toList();
	}

	@Override
	public Event saveEvent(EventInputDto eventInputDto) {
		return eventRepository.save(new Event(eventInputDto));
	}

	@Override
	public EventOutputDto createTeamInEvent(Long eventId, TeamInputDto teamInputDto) {
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new EntityNotFoundException("Event not found"));

		Team team = new Team(teamInputDto);
		team.setEvent(event);
		event.putTeam(team);

		return new EventOutputDto(eventRepository.save(event));
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
