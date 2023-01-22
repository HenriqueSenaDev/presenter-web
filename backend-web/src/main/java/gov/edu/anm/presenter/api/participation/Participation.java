package gov.edu.anm.presenter.api.participation;

import javax.persistence.*;

import gov.edu.anm.presenter.api.event.EventRole;

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
}
