package gov.edu.anm.presenter.repositories;

import java.util.List;

import gov.edu.anm.presenter.entities.Team;

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
