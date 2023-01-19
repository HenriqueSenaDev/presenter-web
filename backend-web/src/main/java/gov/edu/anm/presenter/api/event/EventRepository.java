package gov.edu.anm.presenter.api.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

	Event findByName(String name);

	Event findByCode(Integer code);
}
