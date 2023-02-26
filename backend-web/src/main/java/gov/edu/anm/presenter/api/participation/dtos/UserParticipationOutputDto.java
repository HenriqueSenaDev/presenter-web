package gov.edu.anm.presenter.api.participation.dtos;

import gov.edu.anm.presenter.api.event.EventRole;
import gov.edu.anm.presenter.api.event.dtos.EventParticipationDto;
import gov.edu.anm.presenter.api.participation.Participation;
import lombok.Data;

@Data
public class UserParticipationOutputDto {
    private EventParticipationDto event;
    private EventRole eventRole;

    public UserParticipationOutputDto(Participation participation) {
        this.event = new EventParticipationDto(participation.getId().getEvent());
        this.eventRole = participation.getEventRole();
    }
}
