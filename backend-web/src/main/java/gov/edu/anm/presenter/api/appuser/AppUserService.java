package gov.edu.anm.presenter.api.appuser;

import java.util.List;

import gov.edu.anm.presenter.api.participation.Participation;

public interface AppUserService {

    AppUser findUserById(Long id);

    AppUser findUserByUsername(String username);

    AppUser findUserByToken(String token);

    List<AppUser> findAllUsers();

    List<Participation> findUserParticipations(Long id);

    AppUser saveUser(AppUser appUser);

    AppUser updateUser(AppUser appUser, Long id);

    void deleteUser(Long id);

}
