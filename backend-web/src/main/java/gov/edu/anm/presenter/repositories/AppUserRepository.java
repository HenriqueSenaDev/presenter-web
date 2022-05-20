package gov.edu.anm.presenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.edu.anm.presenter.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findByUsername(String username);

}
