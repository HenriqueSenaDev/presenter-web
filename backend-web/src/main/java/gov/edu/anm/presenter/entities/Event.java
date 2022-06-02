package gov.edu.anm.presenter.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer code;
    private Integer jurorCode;

    @OneToMany(mappedBy = "id.event")
    @JsonIgnore
    Set<Participation> participations = new HashSet<>();

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    Set<Team> teams = new HashSet<>();

}
