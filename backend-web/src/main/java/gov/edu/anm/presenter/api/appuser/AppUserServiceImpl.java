package gov.edu.anm.presenter.api.appuser;

import java.util.List;
import java.util.stream.Collectors;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.auth.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import gov.edu.anm.presenter.api.participation.Participation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUserOutputDto findUserById(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        return new AppUserOutputDto(appUser);
    }

    @Override
    public AppUserOutputDto findUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        return new AppUserOutputDto(appUser);
    }

    @Override
    public AppUserOutputDto findUserByToken(String token) {
        final String username = jwtService.extractUsername(token);
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        return new AppUserOutputDto(appUser);
    }

    @Override
    public List<AppUserOutputDto> findAllUsers() {
        return appUserRepository.findAll().stream()
                .map(AppUserOutputDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Participation> findUserParticipations(Long id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        return List.copyOf(user.getParticipations());
    }

    @Override
    public AppUserOutputDto saveUser(AppUser appUser) {
        AppUser existingUser = appUserRepository.findByUsername(appUser.getUsername()).orElse(null);
        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The username " + appUser.getUsername() + " is already in use.");
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return new AppUserOutputDto(appUserRepository.save(appUser));
    }

    @Override
    public AppUserOutputDto updateUser(AppUser appUser, Long id) {
        AppUser user = appUserRepository.findById(id).orElseThrow();
        if (appUser.getUsername() != null) {
            user.setUsername(appUser.getUsername());
        }
        if (appUser.getPassword() != null) {
            user.setPassword(appUser.getPassword());
        }
        return new AppUserOutputDto(appUserRepository.saveAndFlush(user));
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

}
