package gov.edu.anm.presenter.api.common.dtos.team;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamInputDto {
    private String name;
    private String project;
    private String classroom;
    private List<String> members = new ArrayList<>();
}
