package gov.edu.anm.presenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.edu.anm.presenter.entities.EventRole;

public interface EventRoleRepository extends JpaRepository<EventRole, Long> {

    public EventRole findByName(String name);

}
