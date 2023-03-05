package com.trustrace.backendspring.service;

import com.trustrace.backendspring.model.Employee;
import com.trustrace.backendspring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("service2")
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    //create
    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.insert(employee);
    }

    @Override
    public void createManyEmployee(List<Employee> employees) {
        employeeRepository.insert (employees);
    }

}
