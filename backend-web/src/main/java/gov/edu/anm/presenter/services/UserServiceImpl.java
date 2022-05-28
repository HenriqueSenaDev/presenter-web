package gov.edu.anm.presenter.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.AppRole;
import gov.edu.anm.presenter.repositories.AppUserRepository;
import gov.edu.anm.presenter.repositories.AppRoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;

    // UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found in the database.");
        }
        return new CustomUserDetails(appUser);
    }

    // UserService(custom)
    // AppUser methods
    @Override
    public AppUser findUserById(Long id) {
        return appUserRepository.findById(id).get();
    }

    @Override
    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppRole> findUserAppRoles(String username) {
        return List.copyOf(appUserRepository.findByUsername(username).getRoles());
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser updateUser(AppUser appUser, Long id) {
        AppUser user = appUserRepository.findById(id).get();
        if (appUser.getUsername() != null) {
            user.setUsername(appUser.getUsername());
        }
        if (appUser.getPassword() != null) {
            user.setPassword(appUser.getPassword());
        }
        return appUserRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    // AppRole methods
    @Override
    public AppRole findRoleById(Long id) {
        return appRoleRepository.findById(id).get();
    }

    @Override
    public List<AppRole> findAllAppRoles() {
        return appRoleRepository.findAll();
    }

    @Override
    public AppRole updateAppRole(AppRole appRole, Long id) {
        AppRole role = appRoleRepository.findById(id).get();
        if (appRole.getName() != null) {
            role.setName(appRole.getName());
        }
        return appRoleRepository.saveAndFlush(role);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void deleteAppRole(Long id) {
        appRoleRepository.deleteById(id);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByName(roleName);
        appUser.getRoles().add(role);
        appUserRepository.saveAndFlush(appUser);
    }

    @Override
    public void removeRoleOfUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByName(roleName);
        user.getRoles().remove(role);
        appUserRepository.saveAndFlush(user);
    }

}
