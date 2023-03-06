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
        employeeRepository.save(employee);
    }

    @Override
    public void createManyEmployee(List<Employee> employees) {
        employeeRepository.insert(employees);
    }

    @Override
    public List<Employee> getAllEmployee() {
        if (employeeRepository.findAll ().isEmpty ()){
            throw new RuntimeException ("No Records found");
        }
        else {
            return employeeRepository.findAll ();
        }
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employeeRepository.findById (id).orElseThrow (()->new RuntimeException (String
                .format ("Cannot find employee id %s",id)));
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        if (employeeRepository.findByName(name).isEmpty ()){
            throw new RuntimeException (String.format ("Cannot find employee By name %s",name));
        }
        else {
            return employeeRepository.findByName (name);
        }
    }


}
