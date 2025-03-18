package com.jrp.pma.controllers;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    IEmployeeRepository employeeRepo;

    @GetMapping
    public String displayEmployees(Model model){
        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){
        //Create an instance of Project
        Employee anEmployee =  new Employee();
        // bind Project to the form
        model.addAttribute("employee", anEmployee);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Employee employee, Model model){
        // this should handle saving to the database ....
        // save to the database using an employee crud repository
        employeeRepo.save(employee);
        // use a redirect to prevent duplicate submission
        return "redirect:/employees/new";
    }
}
