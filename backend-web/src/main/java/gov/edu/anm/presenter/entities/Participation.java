package gov.edu.anm.presenter.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Participation {
    @EmbeddedId
    private ParticipationPK id = new ParticipationPK();

    @ManyToOne
    private EventRole eventRole;

    @ManyToOne
    private Team team;

    public Participation() {
    }

    public ParticipationPK getId() {
        return this.id;
    }

    public void setId(ParticipationPK id) {
        this.id = id;
    }

    public EventRole getEventRole() {
        return this.eventRole;
    }

    public void setEventRole(EventRole eventRole) {
        this.eventRole = eventRole;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
