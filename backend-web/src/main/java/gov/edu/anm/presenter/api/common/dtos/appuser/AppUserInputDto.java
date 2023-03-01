package gov.edu.anm.presenter.api.common.dtos.appuser;

import gov.edu.anm.presenter.domain.appuser.AppRole;
import lombok.Data;

@Data
public class AppUserInputDto {
    private String username;
    private String password;
    private AppRole role;
}
