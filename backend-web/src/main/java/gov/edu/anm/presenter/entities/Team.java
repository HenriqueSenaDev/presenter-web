package gov.edu.anm.presenter.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    @ManyToOne
    private Event event;

    public Team(String name, String project, String classRoom, Boolean presented) {
        this.name = name;
        this.project = project;
        this.classRoom = classRoom;
        this.presented = presented;
    }

}
