package gov.edu.anm.presenter.api.appuser.dtos;

import gov.edu.anm.presenter.api.appuser.AppRole;
import lombok.Data;

@Data
public class AppUserInputDto {
    private String username;
    private String password;
    private AppRole role;
}
