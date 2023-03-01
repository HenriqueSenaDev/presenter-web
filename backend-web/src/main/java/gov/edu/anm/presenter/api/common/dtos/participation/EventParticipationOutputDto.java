package gov.edu.anm.presenter.api.common.dtos.participation;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.domain.event.EventRole;
import gov.edu.anm.presenter.domain.participation.Participation;
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
