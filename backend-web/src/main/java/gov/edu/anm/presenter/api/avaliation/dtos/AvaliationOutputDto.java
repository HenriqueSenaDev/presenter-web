package gov.edu.anm.presenter.api.avaliation.dtos;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.api.avaliation.Avaliation;
import gov.edu.anm.presenter.api.team.dtos.TeamAvaliationDto;
import lombok.Data;

@Data
public class AvaliationOutputDto {
    private AppUserOutputDto user;
    private TeamAvaliationDto team;
    private Double value;

    public AvaliationOutputDto(Avaliation avaliation) {
        this.user =  new AppUserOutputDto(avaliation.getId().getUser());
        this.team = new TeamAvaliationDto(avaliation.getId().getTeam());
        this.value = avaliation.getValue();
    }
}
