package gov.edu.anm.presenter.core.auth;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.core.auth.requests.AuthRequest;
import gov.edu.anm.presenter.core.auth.requests.RefreshRequest;
import gov.edu.anm.presenter.core.auth.requests.RegisterRequest;
import gov.edu.anm.presenter.core.auth.responses.AuthResponse;
import gov.edu.anm.presenter.core.auth.responses.RefreshResponse;
import gov.edu.anm.presenter.domain.appuser.AppRole;
import gov.edu.anm.presenter.domain.appuser.AppUser;
import gov.edu.anm.presenter.domain.appuser.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        final AppUser user = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final Map<String, String> tokens = jwtService.generateTokens(user);
        return AuthResponse
                .builder()
                .access_token(tokens.get("access_token"))
                .refresh_token(tokens.get("refresh_token"))
                .profile(new AppUserOutputDto(user))
                .build();
    }

    public RefreshResponse refresh(RefreshRequest request) {
        final String refreshToken = request.getRefresh_token();
        final String username = jwtService.extractUsername(refreshToken);

        final AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final String newAccessToken = jwtService.refreshAccessToken(user);
        return RefreshResponse
                .builder()
                .access_token(newAccessToken)
                .refresh_token(refreshToken)
                .build();
    }

    public AppUserOutputDto register(RegisterRequest request) {
        String encodedPass = passwordEncoder.encode(request.getPassword());
        AppUser user = new AppUser(request.getUsername(), encodedPass, AppRole.USER);

        return new AppUserOutputDto(appUserRepository.save(user));
    }
}
