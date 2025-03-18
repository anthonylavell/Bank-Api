package org.academy.registerapplication.entity;

import javax.persistence.*;

@Entity
@Table(name = "coursecatalog")
public class CourseCatalog {
    @Id
    @SequenceGenerator(name = "get_course_seq", sequenceName = "course_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "get_course_seq")
    private long id;

    private String course;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Column(name = "coursecode")
    private String courseCode;
    private long credit;
    @Column(name = "descr")
    private String description;

    public CourseCatalog(){}

    public CourseCatalog(String course, long credit, String description){
        this.course = course;
        this.credit = credit;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
