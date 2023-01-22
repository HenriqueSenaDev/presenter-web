package gov.edu.anm.presenter.api.participation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gov.edu.anm.presenter.api.event.EventRole;
import gov.edu.anm.presenter.api.team.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARTICIPATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
    @EmbeddedId
    private ParticipationPK id = new ParticipationPK();

    @Enumerated(EnumType.STRING)
    private EventRole eventRole;

    @ManyToOne
    private Team team;
}
