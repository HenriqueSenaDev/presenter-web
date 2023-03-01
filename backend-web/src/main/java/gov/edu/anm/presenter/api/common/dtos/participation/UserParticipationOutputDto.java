package gov.edu.anm.presenter.api.common.dtos.participation;

import gov.edu.anm.presenter.domain.event.EventRole;
import gov.edu.anm.presenter.api.common.dtos.event.EventParticipationDto;
import gov.edu.anm.presenter.domain.participation.Participation;
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
