package com.se.crud_redis_ttmt.controller;

import com.se.crud_redis_ttmt.model.Employee;
import com.se.crud_redis_ttmt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        employeeRepository.saveEmployee(employee);
        return employee;
    }

    @GetMapping
    public Set<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @PutMapping
    public void update(@RequestBody Employee employee){
        employeeRepository.update(employee);
    }

    @DeleteMapping
    public void delete(@RequestBody Employee employee){
        employeeRepository.delete(employee);
    }

}
