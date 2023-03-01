package gov.edu.anm.presenter.core.auth;

import gov.edu.anm.presenter.core.auth.requests.AuthRequestDto;
import gov.edu.anm.presenter.core.auth.responses.AuthResponseDto;
import gov.edu.anm.presenter.core.auth.requests.RefreshRequestDto;
import gov.edu.anm.presenter.core.auth.responses.RefreshResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> authenticate(
            @RequestBody AuthRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponseDto> refreshToken(
            @RequestBody RefreshRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.refresh(request));
    }

}
