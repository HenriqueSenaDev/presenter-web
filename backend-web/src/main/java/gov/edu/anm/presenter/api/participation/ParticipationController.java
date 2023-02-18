package gov.edu.anm.presenter.api.participation;

import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participations")
public class ParticipationController {
    private final ParticipationService participationService;

    @PostMapping("/{userId}/{eventId}")
    public ResponseEntity<ParticipationOutputDto> addJurorParticipation(@PathVariable Long userId, @PathVariable Long eventId) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/participations").toUriString());
        return ResponseEntity.created(uri).body(participationService.addJurorParticipation(userId, eventId));
    }

}
