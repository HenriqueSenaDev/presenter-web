package gov.edu.anm.presenter.api.common.dtos.team;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class TeamUpdateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String project;
    @NotBlank
    private String classroom;
    @NotNull
    private Boolean presented;
    @NotNull
    private List<String> members = new ArrayList<>();
}
