package gov.edu.anm.presenter.api.appuser;

import java.util.List;
import java.util.stream.Collectors;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserInputDto;
import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.api.participation.dtos.UserParticipationOutputDto;
import gov.edu.anm.presenter.auth.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

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
    public List<AppUserOutputDto> findAllUsers() {
        return appUserRepository.findAll().stream()
                .map(AppUserOutputDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserParticipationOutputDto> findUserParticipations(Long id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        return user.getParticipations().stream().map(UserParticipationOutputDto::new).toList();
    }

    @Override
    public AppUser saveUser(AppUserInputDto appUserInputDto) {
        appUserRepository.findByUsername(appUserInputDto.getUsername())
            .ifPresent(user -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The username " + user.getUsername() + " is already in use.");
            });

        appUserInputDto.setPassword(passwordEncoder.encode(appUserInputDto.getPassword()));
        return appUserRepository.save(new AppUser(appUserInputDto));
    }

    @Override
    public AppUser updateUser(AppUserInputDto appUserInputDto, Long id) {
        appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        appUserInputDto.setPassword(passwordEncoder.encode(appUserInputDto.getPassword()));

        AppUser userToUpdate = new AppUser(appUserInputDto);
        userToUpdate.setId(id);
        return appUserRepository.saveAndFlush(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

}
