package gov.edu.anm.presenter.api.common.requests.avaliations;

import lombok.Data;

@Data
public class AddUserAvaliationRequest {
    private Long teamId;
    private Double value;
}
