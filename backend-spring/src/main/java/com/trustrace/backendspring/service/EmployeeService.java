package com.trustrace.backendspring.service;

import com.trustrace.backendspring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    void createEmployee(Employee employee);

    void createManyEmployee(List<Employee> employees);

    List<Employee> getAllEmployee();

    Employee getEmployeeById(String id);

    List<Employee> getEmployeeByName(String name);

}
