package gov.edu.anm.presenter.api.event;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gov.edu.anm.presenter.api.participation.Participation;
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
    private Integer code;
    private Integer jurorCode;
    private String description;

    @OneToMany(mappedBy = "id.event")
    @JsonIgnore
    Set<Participation> participations = new HashSet<>();

}
