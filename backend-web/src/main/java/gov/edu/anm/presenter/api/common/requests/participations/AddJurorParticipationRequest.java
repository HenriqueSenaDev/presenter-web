package gov.edu.anm.presenter.api.common.requests.participations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddJurorParticipationRequest {
    private Long userId;
    private String joinCode;
    private String jurorCode;
}
