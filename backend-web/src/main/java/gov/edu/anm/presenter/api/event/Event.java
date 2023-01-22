package gov.edu.anm.presenter.api.event;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.team.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(exclude = "participations")
@Entity
@Table(name = "EVENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String joinCode;
    private String jurorCode;
    private String description;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "id.event", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Participation> participations = new HashSet<>();

    public Event(EventInputDto eventInputDto) {
        this.name = eventInputDto.getName();
        this.joinCode = eventInputDto.getJoinCode();
        this.jurorCode = eventInputDto.getJurorCode();
        this.description = eventInputDto.getDescription();
    }

}
