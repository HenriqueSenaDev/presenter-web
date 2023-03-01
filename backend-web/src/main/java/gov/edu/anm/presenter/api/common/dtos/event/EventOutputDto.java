package gov.edu.anm.presenter.api.common.dtos.event;

import gov.edu.anm.presenter.domain.event.Event;
import gov.edu.anm.presenter.domain.team.Team;
import lombok.Data;

import java.util.List;

@Data
public class EventOutputDto {
    private Long id;
    private String name;
    private String description;
    private List<Team> teams;

    public EventOutputDto(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.teams = List.copyOf(event.getTeams());
    }
}
