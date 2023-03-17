package gov.edu.anm.presenter.api.controllers.admin;

import gov.edu.anm.presenter.api.common.dtos.team.TeamUpdateDto;
import gov.edu.anm.presenter.domain.avaliations.Avaliation;
import gov.edu.anm.presenter.domain.team.Team;
import gov.edu.anm.presenter.domain.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/teams")
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

    @GetMapping(value = "/{id}/avaliations")
    public ResponseEntity<List<Avaliation>> findTeamAvaliations(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamService.findTeamAvaliations(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamUpdateDto teamUpdateDto) {
        return ResponseEntity.ok().body(teamService.updateTeam(teamUpdateDto, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
