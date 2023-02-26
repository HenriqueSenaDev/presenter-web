package gov.edu.anm.presenter.api.team.dtos;

import gov.edu.anm.presenter.api.team.Team;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamOutputDto {
    private Long id;
    private String name;
    private String project;
    private String classroom;
    private Boolean presented;
    private List<String> members = new ArrayList<>();

    public TeamOutputDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.project = team.getProject();
        this.classroom = team.getClassroom();
        this.presented = team.getPresented();
        this.members = team.getMembers();
    }
}
