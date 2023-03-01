package gov.edu.anm.presenter.api.common.utils;

import gov.edu.anm.presenter.domain.appuser.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileUtilities {
    public UserDetails getAuthenticatedUser() {
        var userToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (AppUser) userToken.getPrincipal();
    }
}
