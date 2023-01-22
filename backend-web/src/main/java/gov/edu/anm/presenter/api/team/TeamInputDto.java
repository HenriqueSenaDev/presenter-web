package gov.edu.anm.presenter.api.team;

import lombok.Data;

@Data
public class TeamInputDto {
    private Long id;
    private String name;
    private String project;
    private String classroom;
}
