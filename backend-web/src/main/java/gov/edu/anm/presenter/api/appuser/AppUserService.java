package gov.edu.anm.presenter.api.appuser;

import java.util.List;

import gov.edu.anm.presenter.api.appuser.dtos.AppUserOutputDto;
import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;

public interface AppUserService {

    AppUserOutputDto findUserById(Long id);

    AppUserOutputDto findUserByUsername(String username);

    AppUserOutputDto findUserByToken(String token);

    List<AppUserOutputDto> findAllUsers();

    List<ParticipationOutputDto> findUserParticipations(Long id);

    AppUserOutputDto saveUser(AppUser appUser);

    AppUserOutputDto updateUser(AppUser appUser, Long id);

    void deleteUser(Long id);

}
