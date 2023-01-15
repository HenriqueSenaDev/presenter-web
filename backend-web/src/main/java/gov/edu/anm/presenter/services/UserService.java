package gov.edu.anm.presenter.services;

import java.util.List;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Participation;

public interface UserService {

    AppUser findUserById(Long id);

    AppUser findUserByUsername(String username);

    AppUser findUserByToken(String token);

    List<AppUser> findAllUsers();

    List<Participation> findUserParticipations(Long id);

    AppUser saveUser(AppUser appUser);

    AppUser updateUser(AppUser appUser, Long id);

    void deleteUser(Long id);

}
