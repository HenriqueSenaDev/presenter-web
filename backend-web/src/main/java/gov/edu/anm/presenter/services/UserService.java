package gov.edu.anm.presenter.services;

import java.util.List;

import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Role;

public interface UserService {

    public AppUser saveUser(AppUser appUser);

    public Role saveRole(Role role);

    public void addRoleToUser(String username, String role);

    public AppUser getUser(String username);

    public List<AppUser> findAll();

}
