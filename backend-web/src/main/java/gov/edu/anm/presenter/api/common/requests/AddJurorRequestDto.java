package gov.edu.anm.presenter.api.common.requests;

import lombok.Data;

@Data
public class AddJurorRequestDto {
    private Long userId;
    private String joinCode;
    private String jurorCode;
}
