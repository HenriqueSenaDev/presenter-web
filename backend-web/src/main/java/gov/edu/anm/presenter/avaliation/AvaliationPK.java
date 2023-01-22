package gov.edu.anm.presenter.avaliation;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gov.edu.anm.presenter.api.appuser.AppUser;
import gov.edu.anm.presenter.api.team.Team;
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
public class AvaliationPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    
}
