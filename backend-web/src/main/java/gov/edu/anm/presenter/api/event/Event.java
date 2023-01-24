package gov.edu.anm.presenter.api.event;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gov.edu.anm.presenter.api.event.dtos.EventCreateDto;
import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.team.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @EqualsAndHashCode.Exclude
    private Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "id.event", cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Participation> participations = new HashSet<>();

    public Event(EventCreateDto eventCreateDto) {
        this.name = eventCreateDto.getName();
        this.joinCode = eventCreateDto.getJoinCode();
        this.jurorCode = eventCreateDto.getJurorCode();
        this.description = eventCreateDto.getDescription();
    }

    public void putTeam(Team team) {
        this.teams.add(team);
    }

}
