package gov.edu.anm.presenter.api.admin;

import gov.edu.anm.presenter.api.common.dtos.participation.ParticipationOutputDto;
import gov.edu.anm.presenter.api.common.dtos.participation.UserParticipationOutputDto;
import gov.edu.anm.presenter.api.common.requests.participations.AddParticipationRequest;
import gov.edu.anm.presenter.domain.participation.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/participations")
public class ParticipationController {
    private final ParticipationService participationService;

    @GetMapping()
    public ResponseEntity<ParticipationOutputDto> findById(@RequestParam Long userId, @RequestParam Long eventId) {
        return ResponseEntity.ok(participationService.findById(userId, eventId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParticipationOutputDto>> findAll() {
        return ResponseEntity.ok(participationService.findAll());
    };

    @PutMapping
    public ResponseEntity<UserParticipationOutputDto> addParticipation(@RequestBody AddParticipationRequest request) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/participations").toUriString());
        return ResponseEntity.created(uri)
                .body(participationService.addParticipation(request.getUserId(), request.getEventId(), request.getRole()));
    }

    @DeleteMapping
    public ResponseEntity<?> removeParticipation(@RequestParam Long userId, @RequestParam Long eventId) {
        participationService.removeParticipation(userId, eventId);
        return ResponseEntity.noContent().build();
    }

}
