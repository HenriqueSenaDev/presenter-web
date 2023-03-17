package gov.edu.anm.presenter.api.common.requests.avaliations;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddUserAvaliationRequest {
    @NotNull
    private Long teamId;
    @NotNull
    private Double value;
}
