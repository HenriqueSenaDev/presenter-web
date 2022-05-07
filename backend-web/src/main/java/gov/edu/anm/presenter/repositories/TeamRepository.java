package gov.edu.anm.presenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.edu.anm.presenter.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
