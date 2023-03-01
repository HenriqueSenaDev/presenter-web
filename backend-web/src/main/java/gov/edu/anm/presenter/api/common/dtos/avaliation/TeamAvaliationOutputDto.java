package gov.edu.anm.presenter.api.common.dtos.avaliation;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.domain.avaliations.Avaliation;
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
