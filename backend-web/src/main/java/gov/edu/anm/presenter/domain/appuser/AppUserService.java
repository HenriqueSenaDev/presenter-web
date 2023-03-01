package gov.edu.anm.presenter.domain.appuser;

import java.util.List;

import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserInputDto;
import gov.edu.anm.presenter.api.common.dtos.appuser.AppUserOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;

public interface AppUserService {

    AppUserOutputDto findUserById(Long id);

    AppUserOutputDto findUserByUsername(String username);

    List<AppUserOutputDto> findAllUsers();

    List<UserParticipationOutputDto> findUserParticipations(Long id);

    AppUser saveUser(AppUserInputDto appUser);

    AppUser updateUser(AppUserInputDto appUser, Long id);

    void deleteUser(Long id);

}
