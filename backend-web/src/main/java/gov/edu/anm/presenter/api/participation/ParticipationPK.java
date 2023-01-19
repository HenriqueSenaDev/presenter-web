package gov.edu.anm.presenter.api.participation;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gov.edu.anm.presenter.api.appuser.AppUser;
import gov.edu.anm.presenter.api.event.Event;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    
}
