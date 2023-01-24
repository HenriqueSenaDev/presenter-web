package gov.edu.anm.presenter.api.appuser.dtos;

import gov.edu.anm.presenter.api.appuser.AppRole;
import gov.edu.anm.presenter.api.appuser.AppUser;
import lombok.Data;

@Data
public class AppUserOutputDto {
    private Long id;
    private String username;
    private AppRole role;

    public AppUserOutputDto(AppUser appUser) {
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.role = appUser.getRole();
    }

}
