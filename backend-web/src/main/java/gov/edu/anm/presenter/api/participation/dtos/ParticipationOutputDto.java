package gov.edu.anm.presenter.api.participation.dtos;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.api.event.EventRole;
import gov.edu.anm.presenter.api.event.dtos.EventParticipationDto;
import gov.edu.anm.presenter.api.participation.Participation;
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
