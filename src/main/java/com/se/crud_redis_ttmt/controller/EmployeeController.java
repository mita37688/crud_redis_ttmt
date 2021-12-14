package com.se.crud_redis_ttmt.controller;

import com.se.crud_redis_ttmt.model.Employee;
import com.se.crud_redis_ttmt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id){
        return employeeRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Employee employee, @PathVariable int id){
        employeeRepository.delete(id);
        employeeRepository.update(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        employeeRepository.delete(id);
    }

}
