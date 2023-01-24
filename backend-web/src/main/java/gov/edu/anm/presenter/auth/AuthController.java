package gov.edu.anm.presenter.auth;

import gov.edu.anm.presenter.auth.dtos.AuthRequestDto;
import gov.edu.anm.presenter.auth.dtos.AuthResponseDto;
import gov.edu.anm.presenter.auth.dtos.RefreshRequestDto;
import gov.edu.anm.presenter.auth.dtos.RefreshResponseDto;
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
