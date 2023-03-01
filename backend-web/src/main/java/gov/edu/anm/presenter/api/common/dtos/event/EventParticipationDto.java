package gov.edu.anm.presenter.api.common.dtos.event;

import gov.edu.anm.presenter.domain.event.Event;
import lombok.Data;

@Data
public class EventParticipationDto {
    private Long id;
    private String name;
    private String description;

    public EventParticipationDto(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}
