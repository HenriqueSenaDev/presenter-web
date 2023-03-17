package gov.edu.anm.presenter.api.controllers.admin;

import gov.edu.anm.presenter.api.common.requests.avaliations.AddAvaliationRequest;
import gov.edu.anm.presenter.domain.avaliations.Avaliation;
import gov.edu.anm.presenter.domain.avaliations.AvaliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/avaliations")
public class AvaliationController {
    private final AvaliationService avaliationService;

    @GetMapping("/all")
    public ResponseEntity<List<Avaliation>> findAll() {
        return ResponseEntity.ok(avaliationService.findAll());
    }

    @GetMapping
    public ResponseEntity<Avaliation> findById(@RequestParam Long userId, @RequestParam Long teamId) {
        return ResponseEntity.ok(avaliationService.findById(userId, teamId));
    }

    @PutMapping
    public ResponseEntity<Avaliation> addAvaliationToTeam(@Valid @RequestBody AddAvaliationRequest request) {
        return ResponseEntity.ok(avaliationService.addAvaliationToTeam(request.getUserId(), request.getTeamId(), request.getValue()));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAvaliation(@RequestParam Long userId, @RequestParam Long teamId) {
        avaliationService.deleteAvaliation(userId, teamId);
        return ResponseEntity.noContent().build();
    }
}
