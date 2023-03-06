package com.trustrace.backendspring.service;

import com.trustrace.backendspring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("service1")
public class EmployeeService1Impl implements EmployeeService{
    @Override
    public void createEmployee(Employee employee) {

    }

    @Override
    public void createManyEmployee(List<Employee> employees) {

    }

    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return null;
    }

}
