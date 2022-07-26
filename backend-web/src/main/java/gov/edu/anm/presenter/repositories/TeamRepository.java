package gov.edu.anm.presenter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.edu.anm.presenter.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query(nativeQuery = true, value = 
			"SELECT DISTINCT t.* FROM teams t "
			+ "LEFT JOIN participations p "
			+ "	ON p.team_id = t.id "
			+ "LEFT JOIN events e "
			+ "	ON p.event_id = e.id "
			+ "WHERE UPPER(t.\"name\") LIKE UPPER(CONCAT('%',:teamName,'%')) "
			+ "AND e.id = :eventId")
	public List<Team> findEventTeamsByName(@Param("teamName") String teamName, 
			@Param("eventId") Long eventId);
	
}
