package gov.edu.anm.presenter.api.avaliation.dtos;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.api.avaliation.Avaliation;
import lombok.Data;

@Data
public class TeamAvaliationOutputDto {
    private AppUserOutputDto user;
    private Double value;

    public TeamAvaliationOutputDto(Avaliation avaliation) {
        this.user = new AppUserOutputDto(avaliation.getId().getUser());
        this.value = avaliation.getValue();
    }
}
