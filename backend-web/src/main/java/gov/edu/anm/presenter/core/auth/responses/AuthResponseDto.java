package gov.edu.anm.presenter.core.auth.responses;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {

    private String access_token;
    private String refresh_token;
    private AppUserOutputDto profile;

}
