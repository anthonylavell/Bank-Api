package org.academy.registerapplication.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.academy.registerapplication.entity.CourseCatalog;
import org.academy.registerapplication.service.AssignProfessor;
import org.academy.registerapplication.service.AssignProfessorToCourse;
import org.academy.registerapplication.service.NameAndCourseCode;
import org.academy.registerapplication.service.CourseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CourseInformation courseInformation;

    @Autowired
    private AssignProfessorToCourse assignProfessorToCourse;

    @Autowired
    ObjectMapper mapper;

    @GetMapping("/status")
    public ObjectNode getStatus(){
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("status","OK");
        return objectNode;
    }

    @GetMapping("/courses")
    public List<CourseCatalog> getAllCourse(){
        return courseInformation.getAllCourses();
    }

    @GetMapping("/course/{courseCode}")
    public CourseCatalog getCourse(@PathVariable("courseCode") String courseCode){

        return courseInformation.getCourse(courseCode);
    }

}
