package gov.edu.anm.presenter.api.event;

import java.util.List;

import gov.edu.anm.presenter.api.team.TeamRepository;
import gov.edu.anm.presenter.api.team.Team;

public enum EventTeamsQuery {

	NAME {
		@Override
		public List<Team> query(String value, Long eventId, TeamRepository repository) {
			return repository.findEventTeamsByName(value, eventId);
		}
	},
	PROJECT {
		@Override
		public List<Team> query(String value, Long eventId, TeamRepository repository) {
			return repository.findEventTeamsByProject(value, eventId);
		}
	},
	MEMBER {
		@Override
		public List<Team> query(String value, Long eventId, TeamRepository repository) {
			return repository.findEventTeamsByMember(value, eventId);
		}
	};
	
	public abstract List<Team> query(String value, Long eventId, TeamRepository repository);
	
}
