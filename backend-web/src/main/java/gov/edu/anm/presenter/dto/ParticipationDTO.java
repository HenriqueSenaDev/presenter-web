package gov.edu.anm.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParticipationDTO {
    private String eventName;
    private String userName;
    private String roleName;
    private String teamName;
}
