package gov.edu.anm.presenter.api.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRoleRepository extends JpaRepository<EventRole, Long> {

    public EventRole findByName(String name);

}
