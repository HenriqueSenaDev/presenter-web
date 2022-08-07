package gov.edu.anm.presenter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.edu.anm.presenter.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public Optional<AppUser> findByUsername(String username);

}
