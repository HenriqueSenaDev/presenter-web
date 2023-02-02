package gov.edu.anm.presenter.api.team.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamCreateDto {
    private String name;
    private String project;
    private String classroom;
    private List<String> members = new ArrayList<>();
}
