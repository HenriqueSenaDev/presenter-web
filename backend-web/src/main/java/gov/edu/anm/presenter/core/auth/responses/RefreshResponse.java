package gov.edu.anm.presenter.core.auth.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshResponse {

    private String access_token;
    private String refresh_token;

}
