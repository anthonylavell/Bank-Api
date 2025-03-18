package com.jrp.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {


    //@GeneratedValue(strategy = GenerationType.AUTO)
    // leave it up to the data to create unique key
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    private long projectId;

    private String name;
    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
    private String description;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
     @JoinTable(name="project_employee",
             joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
     )

    @JsonIgnore
    private List<Employee> employees;

    public Project(){

    }

    public Project(String nam, String stage, String desc) {
        this.name = nam;
        this.stage = stage;
        this.description = desc;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String nam) {
        this.name = nam;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // convenience method:
    public void addEmployee(Employee emp){
        if(employees == null){
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }
}
