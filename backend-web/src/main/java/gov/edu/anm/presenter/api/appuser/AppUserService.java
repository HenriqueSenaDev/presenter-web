package gov.edu.anm.presenter.api.appuser;

import java.util.List;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserInputDto;
import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.api.participation.dtos.UserParticipationOutputDto;

public interface AppUserService {

    AppUserOutputDto findUserById(Long id);

    AppUserOutputDto findUserByUsername(String username);

    List<AppUserOutputDto> findAllUsers();

    List<UserParticipationOutputDto> findUserParticipations(Long id);

    AppUser saveUser(AppUserInputDto appUser);

    AppUser updateUser(AppUserInputDto appUser, Long id);

    void deleteUser(Long id);

}
