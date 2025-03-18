package org.academy.registerapplication.service;

import org.academy.registerapplication.entity.CourseCatalog;
import org.academy.registerapplication.repository.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInformation {
    @Autowired
    ICatalogRepository catalogRepo;

    public List<CourseCatalog> getAllCourses(){
        return catalogRepo.findAll();
    }

    public CourseCatalog getCourse(String courseCode){
        return catalogRepo.findByCourseCode(courseCode);
    }
}

