package com.ross.employino.controllers;

import com.ross.employino.entities.Employee;
import com.ross.employino.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save-employee")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-employee";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
       return "redirect:/" ;
    }

    @GetMapping("/add-employee")
    public String showAddEmployeeForm(Employee employee) {
        return "add-employee";
    }

    @GetMapping("/")
    public String showEmployeeList(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }
}
