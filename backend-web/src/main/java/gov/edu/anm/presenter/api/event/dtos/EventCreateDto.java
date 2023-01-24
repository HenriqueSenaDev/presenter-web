package gov.edu.anm.presenter.api.event.dtos;

import lombok.Data;

@Data
public class EventCreateDto {
    private Long id;
    private String name;
    private String joinCode;
    private String jurorCode;
    private String description;
}
