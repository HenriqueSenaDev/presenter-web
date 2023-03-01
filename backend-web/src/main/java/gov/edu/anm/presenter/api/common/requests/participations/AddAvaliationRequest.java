package gov.edu.anm.presenter.api.common.requests.participations;

import lombok.Data;

@Data
public class AddAvaliationRequest {
    private Long userId;
    private Long teamId;
    private Double value;
}
