package gov.edu.anm.presenter.services;

import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.entities.Participation;

public interface EventService {
    public Event saveEvent(Event event);

    public EventRole saveEventRole(EventRole eventRole);

    public Participation addParticipation(Long appUserId, Long eventId, Long eventRoleId, Long teamId);
}
