package gov.edu.anm.presenter.auth;

import gov.edu.anm.presenter.api.appuser.AppUser;
import gov.edu.anm.presenter.api.appuser.AppUserRepository;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.auth.dtos.AuthRequestDto;
import gov.edu.anm.presenter.auth.dtos.AuthResponseDto;
import gov.edu.anm.presenter.auth.dtos.RefreshRequestDto;
import gov.edu.anm.presenter.auth.dtos.RefreshResponseDto;
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

    public AuthResponseDto authenticate(AuthRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        System.out.println("ok");
        final AppUser user = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final Map<String, String> tokens = jwtService.generateTokens(user);
        return AuthResponseDto
                .builder()
                .access_token(tokens.get("access_token"))
                .refresh_token(tokens.get("refresh_token"))
                .profile(new AppUserOutputDto(user))
                .build();
    }

    public RefreshResponseDto refresh(RefreshRequestDto request) {
        final String refreshToken = request.getRefresh_token();
        final String username = jwtService.extractUsername(refreshToken);

        final AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final String newAccessToken = jwtService.refreshAccessToken(user);
        return RefreshResponseDto
                .builder()
                .access_token(newAccessToken)
                .refresh_token(refreshToken)
                .build();
    }
}
