package gov.edu.anm.presenter.api.common.dtos.participation;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.domain.event.EventRole;
import gov.edu.anm.presenter.api.common.dtos.event.EventParticipationDto;
import gov.edu.anm.presenter.domain.participation.Participation;
import lombok.Data;

@Data
public class ParticipationOutputDto {
    private EventRole eventRole;
    private AppUserOutputDto user;
    private EventParticipationDto event;

    public ParticipationOutputDto(Participation participation) {
        this.user = new AppUserOutputDto(participation.getId().getUser());
        this.event = new EventParticipationDto(participation.getId().getEvent());
        this.eventRole = participation.getEventRole();
    }
}
