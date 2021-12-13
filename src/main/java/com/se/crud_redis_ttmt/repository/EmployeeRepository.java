package com.se.crud_redis_ttmt.repository;

import com.se.crud_redis_ttmt.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private static final String KEY = "EMPLOYEE";

    private HashOperations hashOperations;//crud hash

    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee employee){
        hashOperations.put(KEY, employee.getId(), employee);
    }

    public List<Employee> findAll(){
        return hashOperations.values(KEY);
    }

    public Employee findById(Integer id){

        return (Employee) hashOperations.get(KEY, id);
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }

    public void delete(Integer id){
        hashOperations.delete(KEY, id);
    }
}
