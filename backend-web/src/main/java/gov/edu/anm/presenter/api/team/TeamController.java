package gov.edu.anm.presenter.api.team;

import gov.edu.anm.presenter.api.avaliation.Avaliation;
import gov.edu.anm.presenter.api.team.dtos.TeamOutputDto;
import gov.edu.anm.presenter.api.team.dtos.TeamUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamOutputDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeamOutputDto>> findAll() {
        return ResponseEntity.ok().body(teamService.findAll());
    }

    @GetMapping(value = "/avaliations/{id}")
    public ResponseEntity<List<Avaliation>> findTeamAvaliations(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamService.findTeamAvaliations(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody TeamUpdateDto teamUpdateDto) {
        return ResponseEntity.ok().body(teamService.updateTeam(teamUpdateDto, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
