package gov.edu.anm.presenter.api.common.dtos.team;

import gov.edu.anm.presenter.domain.team.Team;
import lombok.Data;

@Data
public class TeamAvaliationDto {
    private Long id;
    private String name;
    private String project;

    public TeamAvaliationDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.project = team.getProject();
    }
}
