package gov.edu.anm.presenter.api.participation.dtos;

import lombok.Data;

@Data
public class AddJurorRequestDto {
    private Long userId;
    private String joinCode;
    private String jurorCode;
}
