package gov.edu.anm.presenter.api.common.requests.participations;

import lombok.Data;

@Data
public class AddUserJurorParticipationRequest {
    private String joinCode;
    private String jurorCode;
}
