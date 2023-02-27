package gov.edu.anm.presenter.api.participation.dtos;

import lombok.Data;

@Data
public class AddSpectatorRequestDto {
    private Long userId;
    private String joinCode;
}
