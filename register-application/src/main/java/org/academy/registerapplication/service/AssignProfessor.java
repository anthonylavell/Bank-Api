package org.academy.registerapplication.service;

import org.springframework.stereotype.Service;

@Service
public class AssignProfessor {
    private String professorNam="";
    private String courseNam="";
    private long credit;


    public String getName() {
        return professorNam;
    }

    public void setName(String name) {
        this.professorNam = name;
    }

    public String getCourse() {
        return courseNam;
    }

    public void setCourse(String course) {
        this.courseNam = course;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }
}
