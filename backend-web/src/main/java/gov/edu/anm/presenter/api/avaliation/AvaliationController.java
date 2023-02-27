package gov.edu.anm.presenter.api.avaliation;

import gov.edu.anm.presenter.api.avaliation.dtos.AddAvaliationRequestDto;
import gov.edu.anm.presenter.api.avaliation.dtos.AvaliationOutputDto;
import gov.edu.anm.presenter.api.avaliation.dtos.TeamAvaliationOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/avaliations")
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
    public ResponseEntity<TeamAvaliationOutputDto> addAvaliationToTeam(@RequestBody AddAvaliationRequestDto addAvaliationRequestDto) {
        return ResponseEntity.ok(avaliationService.addAvaliationToTeam(addAvaliationRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAvaliation(@RequestParam Long userId, @RequestParam Long teamId) {
        avaliationService.deleteAvaliation(userId, teamId);
        return ResponseEntity.noContent().build();
    }
}
