package com.jrp.pma.controllers;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    IProjectRepository proRepo;

    @Autowired
    IEmployeeRepository employeeRepo;

    @GetMapping
    public String displayEmployees(Model model){
        List<Project> projects = proRepo.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model){
        //Create an instance of Project
        Project aProject = new Project();
        List<Employee> employees = employeeRepo.findAll();
        // bind Project to the form
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees",employees);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProjectForm(Project project, Model model){
       // this should handle saving to the database ....
        proRepo.save(project);

        // use a redirect to prevent duplicate submission
        return "redirect:/projects";
    }
}
