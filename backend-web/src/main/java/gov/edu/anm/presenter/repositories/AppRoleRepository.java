package gov.edu.anm.presenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.edu.anm.presenter.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    public AppRole findByName(String roleName);

}
