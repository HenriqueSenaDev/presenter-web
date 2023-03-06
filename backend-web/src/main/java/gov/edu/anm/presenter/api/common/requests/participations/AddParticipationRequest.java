package gov.edu.anm.presenter.api.common.requests.participations;

import gov.edu.anm.presenter.domain.event.EventRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddParticipationRequest {
    private Long userId;
    private Long eventId;
    private EventRole role;
}
