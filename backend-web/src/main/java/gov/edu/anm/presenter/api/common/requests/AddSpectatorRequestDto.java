package gov.edu.anm.presenter.api.common.requests;

import lombok.Data;

@Data
public class AddSpectatorRequestDto {
    private Long userId;
    private String joinCode;
}
