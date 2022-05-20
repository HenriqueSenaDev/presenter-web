package gov.edu.anm.presenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.edu.anm.presenter.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String roleName);

}
