package gov.edu.anm.presenter.api.team;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gov.edu.anm.presenter.avaliation.Avaliation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Team>> findAll() {
        return ResponseEntity.ok().body(teamService.findAll());
    }

    @GetMapping(value = "/avaliations/{id}")
    public ResponseEntity<List<Avaliation>> findTeamAvaliations(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamService.findTeamAvaliations(id));
    }

//    @GetMapping(value = "/query")
//    public ResponseEntity<List<Team>> findEventTeamsByQuery(@RequestParam String queryBy,
//    		@RequestParam Long eventId,
//    		@RequestParam String value) {
//    	return ResponseEntity.ok(teamService.findEventTeamsByQuery(queryBy, value, eventId));
//    }

    @PostMapping
    public ResponseEntity<Team> saveTeam(@RequestBody TeamInputDto teamInputDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
        return ResponseEntity.created(uri).body(teamService.saveTeam(teamInputDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return ResponseEntity.ok().body(teamService.updateTeam(team, id));
    }

    @PutMapping(value = "/avaliations/add")
    public ResponseEntity<Avaliation> addAvaliation(
            @RequestParam Long teamId,
            @RequestParam Long userId,
            @RequestParam Double value) {
        return ResponseEntity.ok().body(teamService.addAvaliation(teamId, userId, value));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok().body("The Team has been deleted.");
    }

    @DeleteMapping(value = "/avaliations")
    public ResponseEntity<?> deleteAvaliation(@RequestParam Long teamId, @RequestParam Long userId) {
        teamService.deleteAvaliation(teamId, userId);
        return ResponseEntity.ok().body("The Avaliation has been deleted.");
    }
}
