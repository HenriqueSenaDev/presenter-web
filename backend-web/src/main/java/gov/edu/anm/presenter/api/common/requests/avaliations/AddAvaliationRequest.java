package gov.edu.anm.presenter.api.common.requests.avaliations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddAvaliationRequest {
    private Long userId;
    private Long teamId;
    private Double value;
}
