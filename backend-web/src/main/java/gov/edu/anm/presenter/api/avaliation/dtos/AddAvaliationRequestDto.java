package gov.edu.anm.presenter.api.avaliation.dtos;

import lombok.Data;

@Data
public class AddAvaliationRequestDto {
    private Long userId;
    private Long teamId;
    private Double value;
}
