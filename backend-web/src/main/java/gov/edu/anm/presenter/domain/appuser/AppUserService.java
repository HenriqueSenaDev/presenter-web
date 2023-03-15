package gov.edu.anm.presenter.domain.appuser;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserInputDto;
import gov.edu.anm.presenter.domain.participation.Participation;

import java.util.List;

public interface AppUserService {

    AppUser findUserById(Long id);

    AppUser findUserByUsername(String username);

    List<AppUser> findAllUsers();

    List<Participation> findUserParticipations(Long id);

    AppUser saveUser(AppUserInputDto appUser);

    AppUser updateUser(AppUserInputDto appUser, Long id);

    void deleteUser(Long id);

}
