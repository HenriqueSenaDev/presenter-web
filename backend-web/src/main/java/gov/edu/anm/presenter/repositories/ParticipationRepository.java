package gov.edu.anm.presenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.edu.anm.presenter.entities.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

}
