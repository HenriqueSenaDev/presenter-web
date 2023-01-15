package gov.edu.anm.presenter.auth;

import gov.edu.anm.presenter.config.JwtService;
import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.repositories.AppUserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        final AppUser user = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final Map<String, String> tokens = jwtService.generateTokens(user);
        return AuthenticationResponseDto
                .builder()
                .access_token(tokens.get("access_token"))
                .refresh_token(tokens.get("refresh_token"))
                .username(user.getUsername())
                .build();
    }

    public AuthenticationResponseDto refresh(RefreshRequestDto request) {
        final String refreshToken = request.getRefresh_token();
        final String username = jwtService.extractUsername(refreshToken);

        final AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final String newAccessToken = jwtService.refreshAccessToken(user);
        return AuthenticationResponseDto
                .builder()
                .access_token(newAccessToken)
                .refresh_token(refreshToken)
                .username(username)
                .build();
    }
}
