package gov.edu.anm.presenter.api.admin;

import gov.edu.anm.presenter.api.common.requests.participations.AddAvaliationRequest;
import gov.edu.anm.presenter.api.common.dtos.avaliation.AvaliationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.avaliation.TeamAvaliationOutputDto;
import gov.edu.anm.presenter.domain.avaliations.AvaliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/avaliations")
public class AvaliationController {
    private final AvaliationService avaliationService;

    @GetMapping("/all")
    public ResponseEntity<List<AvaliationOutputDto>> findAll() {
        return ResponseEntity.ok(avaliationService.findAll());
    }

    @GetMapping
    public ResponseEntity<AvaliationOutputDto> findById(@RequestParam Long userId, @RequestParam Long teamId) {
        return ResponseEntity.ok(avaliationService.findById(userId, teamId));
    }

    @PutMapping
    public ResponseEntity<TeamAvaliationOutputDto> addAvaliationToTeam(@RequestBody AddAvaliationRequest addAvaliationRequest) {
        return ResponseEntity.ok(avaliationService.addAvaliationToTeam(addAvaliationRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAvaliation(@RequestParam Long userId, @RequestParam Long teamId) {
        avaliationService.deleteAvaliation(userId, teamId);
        return ResponseEntity.noContent().build();
    }
}
