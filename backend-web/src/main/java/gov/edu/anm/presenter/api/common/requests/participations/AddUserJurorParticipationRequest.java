package gov.edu.anm.presenter.api.common.requests.participations;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddUserJurorParticipationRequest {
    @NotBlank
    private String joinCode;
    @NotBlank
    private String jurorCode;
}
