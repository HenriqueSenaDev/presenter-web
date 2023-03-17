package gov.edu.anm.presenter.api.common.requests.participations;

import gov.edu.anm.presenter.domain.event.EventRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddParticipationRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long eventId;
    @NotNull
    private EventRole role;
}
