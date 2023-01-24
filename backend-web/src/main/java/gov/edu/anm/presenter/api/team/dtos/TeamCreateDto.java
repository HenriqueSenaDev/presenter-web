package gov.edu.anm.presenter.api.team.dtos;

import lombok.Data;

@Data
public class TeamCreateDto {
    private Long id;
    private String name;
    private String project;
    private String classroom;
}
