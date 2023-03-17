package gov.edu.anm.presenter.api.common.dtos.appuser;

import gov.edu.anm.presenter.domain.appuser.AppRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AppUserInputDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private AppRole role;
}
