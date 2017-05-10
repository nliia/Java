package ru.kpfu.itis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liia
 */

@Entity
@Table(name = "academy_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    private String number;

    private String head;

    @Column(name = "head_phone_number")
    private String headPhoneNumber;

    @Column(name = "students_amount")
    private int studentsAmount;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> schedule = new ArrayList<>();

    public Group(Department department,
                 String number,
                 String head,
                 String headPhoneNumber,
                 int studentsAmount) {
        this.department = department;
        this.number = number;
        this.head = head;
        this.headPhoneNumber = headPhoneNumber;
        this.studentsAmount = studentsAmount;
    }

    public Group() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHeadPhoneNumber() {
        return headPhoneNumber;
    }

    public void setHeadPhoneNumber(String headPhoneNumber) {
        this.headPhoneNumber = headPhoneNumber;
    }

    public int getStudentsAmount() {
        return studentsAmount;
    }

    public void setStudentsAmount(int studentsAmount) {
        this.studentsAmount = studentsAmount;
    }

    public List<Subject> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Subject> schedule) {
        this.schedule = schedule;
    }

    public void addSchedule(Subject subject){
        this.getSchedule().add(subject);
    }
}
