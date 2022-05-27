package gov.edu.anm.presenter.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String project;
    private String classRoom;
    private Double ponctuation;
    private Integer avaliations;
    private Double average;
    private Boolean presented;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<Student> students = new HashSet<>();

    public Team() {

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

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getClassRoom() {
        return this.classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public Double getPonctuation() {
        return this.ponctuation;
    }

    public void setPonctuation(Double ponctuation) {
        this.ponctuation = ponctuation;
    }

    public Integer getAvaliations() {
        return this.avaliations;
    }

    public void setAvaliations(Integer avaliations) {
        this.avaliations = avaliations;
    }

    public Double getAverage() {
        return this.average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public Boolean getPresented() {
        return this.presented;
    }

    public void setPresented(Boolean presented) {
        this.presented = presented;
    }

}
