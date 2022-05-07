package gov.edu.anm.presenter.entities;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private Long id;
    private String name;
    private String project;
    private String classRoom;
    private Double ponctuation;
    private Integer avaliations;
    private Double average;

    private List<Student> students = new ArrayList<>();

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

    public List<Student> getStudents() {
        return this.students;
    }

}
