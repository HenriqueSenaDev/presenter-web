package gov.edu.anm.presenter.api.team;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gov.edu.anm.presenter.api.avaliation.Avaliation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(exclude = "avaliations")
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
    private String classRoom;
    private Double average;
    private Boolean presented;
    private Integer avaliationsQuantity;

    @OneToMany(mappedBy = "id.team")
    @JsonIgnore
    Set<Avaliation> avaliations = new HashSet<>();

    //Custom constructor
    public Team(String name, String project, String classRoom, Boolean presented) {
        this.name = name;
        this.project = project;
        this.classRoom = classRoom;
        this.presented = presented;
    }

}
