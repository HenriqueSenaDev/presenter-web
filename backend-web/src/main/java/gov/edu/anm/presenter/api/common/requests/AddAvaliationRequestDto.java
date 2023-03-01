package gov.edu.anm.presenter.api.common.requests;

import lombok.Data;

@Data
public class AddAvaliationRequestDto {
    private Long userId;
    private Long teamId;
    private Double value;
}
