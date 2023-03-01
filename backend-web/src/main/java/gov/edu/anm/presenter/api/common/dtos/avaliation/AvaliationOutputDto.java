package gov.edu.anm.presenter.api.common.dtos.avaliation;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.domain.avaliations.Avaliation;
import gov.edu.anm.presenter.api.common.dtos.team.TeamAvaliationDto;
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
