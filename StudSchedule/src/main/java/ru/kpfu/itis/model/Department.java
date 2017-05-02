package ru.kpfu.itis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liia
 */

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "high_school_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private HighSchool highSchool;

    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Group> groups = new ArrayList<Group>();

    public Department(HighSchool highSchool, String name) {
        this.highSchool = highSchool;
        this.name = name;
    }

    public Department() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HighSchool getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(HighSchool highSchool) {
        this.highSchool = highSchool;
        if (!highSchool.getDepartments().contains(this))
            highSchool.getDepartments().add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
        if (group.getDepartment() != this) {
            group.setDepartment(this);
        }
    }

}
