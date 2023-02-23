package gov.edu.anm.presenter.api.avaliation.dtos;

import gov.edu.anm.presenter.api.avaliation.Avaliation;
import lombok.Data;

@Data
public class AvaliationOutputDto {
    private Long userId;
    private Long teamId;
    private Double value;

    public AvaliationOutputDto(Avaliation avaliation) {
        this.userId = avaliation.getId().getUser().getId();
        this.teamId = avaliation.getId().getTeam().getId();
        this.value = avaliation.getValue();
    }
}
