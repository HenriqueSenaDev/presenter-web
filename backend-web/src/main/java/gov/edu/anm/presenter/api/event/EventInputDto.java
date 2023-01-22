package gov.edu.anm.presenter.api.event;

import lombok.Data;

@Data
public class EventInputDto {
    private Long id;
    private String name;
    private String joinCode;
    private String jurorCode;
    private String description;
}
