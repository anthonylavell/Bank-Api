package org.academy.registerapplication.api;

import org.academy.registerapplication.entity.CourseCatalog;
import org.academy.registerapplication.exception.CustomExceptionHandler;
import org.academy.registerapplication.exception.RecordNotFoundException;
import org.academy.registerapplication.service.AssignProfessor;
import org.academy.registerapplication.service.AssignProfessorToCourse;
import org.academy.registerapplication.service.NameAndCourseCode;
import org.academy.registerapplication.service.CourseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/assign")
public class AssignProfessorController {

    @Autowired
    private CourseInformation courseInformation;

    @Autowired
    private AssignProfessorToCourse assignProfessorToCourse;

    @PostMapping("/professor")
    public Object assignmentProfessor(@RequestBody NameAndCourseCode nameAndCourseCode){
        if(nameAndCourseCode.getCourseCode() == null) {
            return new CustomExceptionHandler().handleUserNotFoundException(new RecordNotFoundException("Please check airport code"));
        }
        assignProfessorToCourse.addProfessorToCourse(nameAndCourseCode);
        return nameAndCourseCode.getProfessorName()+" is now the Professor of this course";
    }

    @GetMapping("/courses")
    public List<AssignProfessor> getAllCoursesWithProfessors(){
        return assignProfessorToCourse.getAllCoursesWithProfessors();
    }

    @PatchMapping("/modify")
    public String modifyCourseInfo(@RequestBody NameAndCourseCode nameAndCourseCode){
        return assignProfessorToCourse.modifyClass(nameAndCourseCode);

    }

    @DeleteMapping("/delete")
    public String deleteCourseInfo(@RequestBody NameAndCourseCode nameAndCourseCode){
        return assignProfessorToCourse.deleteClass(nameAndCourseCode);

    }

}
