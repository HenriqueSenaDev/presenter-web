package gov.edu.anm.presenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.edu.anm.presenter.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    public Event findByName(String name);

    public Event findByCode(Integer code);

}
