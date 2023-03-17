package gov.edu.anm.presenter.api.common.dtos.event;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EventInputDto {
    @NotBlank
    private String name;
    @NotBlank
    private String joinCode;
    @NotBlank
    private String jurorCode;
    @NotBlank
    private String description;
}
