package gov.edu.anm.presenter.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer code;
    private Integer jurorCode;

    @OneToMany(mappedBy = "id.event")
    Set<Participation> participations = new HashSet<>();

    public Event() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Set<Participation> getParticipations() {
        return this.participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public Integer getJurorCode() {
        return this.jurorCode;
    }

    public void setJurorCode(Integer jurorCode) {
        this.jurorCode = jurorCode;
    }

}
