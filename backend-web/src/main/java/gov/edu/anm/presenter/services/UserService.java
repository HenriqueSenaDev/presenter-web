package gov.edu.anm.presenter.services;

import java.util.List;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.AppRole;

public interface UserService {
    // AppUser methods
    public AppUser findUserById(Long id);

    public AppUser getUserByUsername(String username);

    public List<AppUser> findAllUsers();

    public List<AppRole> findUserAppRoles(String username);

    public AppUser saveUser(AppUser appUser);

    public AppUser updateUser(AppUser appUser, Long id);

    public void deleteUser(Long id);

    // AppRole methods
    public AppRole findRoleById(Long id);

    public List<AppRole> findAllAppRoles();

    public AppRole saveRole(AppRole role);

    public AppRole updateAppRole(AppRole appRole, Long id);

    public void deleteAppRole(Long id);

    public void addRoleToUser(String username, String roleName);

    public void removeRoleOfUser(String username, String roleName);

}
