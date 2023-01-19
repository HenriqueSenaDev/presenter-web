package gov.edu.anm.presenter.api.team;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
	
	@Query(nativeQuery = true, value = 
			"SELECT DISTINCT t.* FROM teams t "
			+ "LEFT JOIN participations p "
			+ "	ON p.team_id = t.id "
			+ "LEFT JOIN events e "
			+ "	ON p.event_id = e.id "
			+ "WHERE UPPER(t.project) LIKE UPPER(CONCAT('%',:project,'%')) "
			+ "AND e.id = :eventId")
	public List<Team> findEventTeamsByProject(@Param("project") String project, 
			@Param("eventId") Long eventId);
	
	@Query(nativeQuery = true, value = 
			"SELECT DISTINCT t.* FROM teams t "
			+ "LEFT JOIN participations p "
			+ " ON p.team_id = t.id "
			+ "LEFT JOIN app_users a "
			+ "	ON p.user_id = a.id "
			+ "LEFT JOIN events e "
			+ "	ON p.event_id = e.id "
			+ "WHERE UPPER(a.username) LIKE UPPER(CONCAT('%',:member,'%')) "
			+ "AND e.id = :eventId")
	public List<Team> findEventTeamsByMember(@Param("member") String member, 
			@Param("eventId") Long eventId);
	
}
