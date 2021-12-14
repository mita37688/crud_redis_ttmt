package com.se.crud_redis_ttmt.repository;

import com.se.crud_redis_ttmt.model.Employee;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private static final String KEY = "EMPLOYEE_LIST";

    private ListOperations listOperations;

    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.listOperations = redisTemplate.opsForList();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee employee){
        listOperations.rightPush(KEY, employee);
    }

    public List<Employee> findAll(){
        if (!redisTemplate.hasKey(KEY)) {
            return null;
        }
        return listOperations.range(KEY, 0, listOperations.size(KEY));
    }

    public Employee findById(Integer id){
        List<Employee> employees = findAll();
        for(Employee employee : employees){
            if(employee.getId() == id){
                return employee;
            }
        }
        return null;
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }

    public void delete(Integer id){
        listOperations.remove(KEY, 1, findById(id));
    }
}
