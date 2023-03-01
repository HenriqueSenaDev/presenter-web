package gov.edu.anm.presenter.api.admin;

import gov.edu.anm.presenter.api.common.dtos.avaliation.TeamAvaliationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamOutputDto;
import gov.edu.anm.presenter.api.common.dtos.team.TeamUpdateDto;
import gov.edu.anm.presenter.domain.team.Team;
import gov.edu.anm.presenter.domain.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/teams")
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
    public ResponseEntity<List<TeamAvaliationOutputDto>> findTeamAvaliations(@PathVariable Long id) {
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
