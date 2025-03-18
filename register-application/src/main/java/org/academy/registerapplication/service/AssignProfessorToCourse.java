package org.academy.registerapplication.service;

import org.academy.registerapplication.entity.CourseCatalog;
import org.academy.registerapplication.repository.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class AssignProfessorToCourse {
    @Autowired
    CourseInformation courseInformation;


    private List<AssignProfessor> listOfProfessor = new LinkedList<>();
    private CourseCatalog course;

    public void addProfessorToCourse( NameAndCourseCode nameAndCourseCode){
        course = courseInformation.getCourse(nameAndCourseCode.getCourseCode());
        AssignProfessor assign = new AssignProfessor();
        assign.setName(nameAndCourseCode.getProfessorName());
        assign.setCourse(course.getCourse());
        assign.setCredit(course.getCredit());
       listOfProfessor.add(assign);
    }

    public List<AssignProfessor> getAllCoursesWithProfessors(){

        return listOfProfessor;
    }

    public String modifyClass(NameAndCourseCode nameAndCourseCode){
        course = courseInformation.getCourse(nameAndCourseCode.getCourseCode());
        for(int i = 0; i < listOfProfessor.size(); i++) {
                if (listOfProfessor.get(i).getCourse().indexOf(course.getCourse()) > -1) {
                    listOfProfessor.get(i).setName(nameAndCourseCode.getProfessorName());
                    return "Course was updated";
                }
        }
        return "Please check course code";
    }

    public String deleteClass(NameAndCourseCode nameAndCourseCode){
        course = courseInformation.getCourse(nameAndCourseCode.getCourseCode());
        for(int i = 0; i < listOfProfessor.size(); i++) {
            if (listOfProfessor.get(i).getCourse().indexOf(course.getCourse()) > -1) {
                listOfProfessor.remove(i);
                return "Course was deleted";
            }
        }

        return "Please check course code";
    }
}
