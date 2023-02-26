package gov.edu.anm.presenter.api.participation.dtos;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.api.event.EventRole;
import gov.edu.anm.presenter.api.participation.Participation;
import lombok.Data;

@Data
public class EventParticipationOutputDto {
    private EventRole eventRole;
    private AppUserOutputDto user;

    public EventParticipationOutputDto(Participation participation) {
        this.eventRole = participation.getEventRole();
        this.user = new AppUserOutputDto(participation.getId().getUser());
    }
}
