package com.se.crud_redis_ttmt.repository;

import com.se.crud_redis_ttmt.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class EmployeeRepository {

    private static final String KEY = "EMPLOYEE_SET";

    private SetOperations setOperations;

    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.setOperations = redisTemplate.opsForSet();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee employee){
        setOperations.add(KEY, employee);
    }

    public Set<Employee> findAll(){
        return setOperations.members(KEY);
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }

    public void delete(Employee employee){
        setOperations.remove(KEY, employee);
    }
}
