package gov.edu.anm.presenter.api.team;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gov.edu.anm.presenter.api.team.dtos.TeamCreateDto;
import gov.edu.anm.presenter.api.avaliation.Avaliation;

import gov.edu.anm.presenter.api.event.Event;
import lombok.*;

@Entity
@Table(name = "TEAMS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String project;
    private String classroom;
    private Boolean presented;

    @ElementCollection(targetClass=String.class)
    @CollectionTable(name = "teams_members", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "list")
    private List<String> members = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    @OneToMany(mappedBy = "id.team", cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Avaliation> avaliations = new HashSet<>();

    public Team(TeamCreateDto teamCreateDto) {
        this.name = teamCreateDto.getName();
        this.project = teamCreateDto.getProject();
        this.classroom = teamCreateDto.getClassroom();
        this.presented = false;
    }

    public Double getAverage() {
        final Optional<Double> count = this.avaliations
                .stream().map(Avaliation::getValue).reduce(Double::sum);

        final double average = count.orElse(0.0) / (double) this.avaliations.size();
        return Double.isNaN(average) ? 0.0 : average;
    }

    public Integer getAvaliationsQuantity() {
        return this.avaliations.size();
    }

}
