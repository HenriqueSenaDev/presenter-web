package gov.edu.anm.presenter.domain.appuser;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserInputDto;
import gov.edu.anm.presenter.domain.exceptions.UnavailableSubjectException;
import gov.edu.anm.presenter.domain.participation.Participation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser findUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<Participation> findUserParticipations(Long id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return List.copyOf(user.getParticipations());
    }

    @Override
    public AppUser saveUser(AppUserInputDto appUserInputDto) {
        appUserRepository.findByUsername(appUserInputDto.getUsername())
            .ifPresent(user -> {
                throw new UnavailableSubjectException("The username " + user.getUsername() + " is already in use.");
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
