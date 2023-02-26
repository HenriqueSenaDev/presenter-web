package gov.edu.anm.presenter.api.event.dtos;

import lombok.Data;

@Data
public class EventInputDto {
    private String name;
    private String joinCode;
    private String jurorCode;
    private String description;
}
