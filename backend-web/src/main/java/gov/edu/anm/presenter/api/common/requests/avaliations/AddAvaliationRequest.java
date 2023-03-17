package gov.edu.anm.presenter.api.common.requests.avaliations;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddAvaliationRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long teamId;
    @NotNull
    private Double value;
}
