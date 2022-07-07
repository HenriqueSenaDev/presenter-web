package gov.edu.anm.presenter.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARTICIPATIONS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Participation {
    @EmbeddedId
    private ParticipationPK id = new ParticipationPK();

    @ManyToOne
    private EventRole eventRole;

    @ManyToOne
    private Team team;
}
