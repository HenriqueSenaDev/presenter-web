package gov.edu.anm.presenter.domain.participation;

import gov.edu.anm.presenter.domain.event.EventRole;

import java.util.List;

public interface ParticipationService {
    Participation findById(Long userId, Long eventId);
    List<Participation> findAll();
    Participation addParticipation(Long userId, Long eventId, EventRole role);
    void removeParticipation(Long userId, Long eventId);
}
