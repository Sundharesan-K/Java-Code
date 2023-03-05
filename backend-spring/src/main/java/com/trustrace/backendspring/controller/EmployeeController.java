package com.trustrace.backendspring.controller;

import com.trustrace.backendspring.model.Employee;
import com.trustrace.backendspring.response.APIResponse;
import com.trustrace.backendspring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Qualifier("service1")
    @Autowired
    private  EmployeeService employeeService;

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse> create(@RequestBody Employee employee){
        APIResponse apiResponse=new APIResponse ();
        try {
            employeeService.createEmployee(employee);
            apiResponse.setStatus ("Success");
            apiResponse.setMessage ("Employee Created SuccessFully");
            return new ResponseEntity<> (apiResponse, HttpStatus.CREATED);
        }catch (Exception e){
            apiResponse.setStatus ("error");
            apiResponse.setErrorCode ("404");
            apiResponse.setStatus (e.getMessage ());
            return new ResponseEntity<> (apiResponse,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/createMany",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse> createMany(@RequestBody List<Employee> employees){
        APIResponse apiResponse=new APIResponse ();
        try {
            employeeService.createManyEmployee(employees);
            apiResponse.setStatus ("Success");
            apiResponse.setMessage ("Records Created SuccessFully");
            return new ResponseEntity<> (apiResponse,HttpStatus.CREATED);
        }catch (Exception e){
            apiResponse.setStatus ("error");
            apiResponse.setErrorCode ("404");
            apiResponse.setMessage (e.getMessage ());
            return new ResponseEntity<> (apiResponse,HttpStatus.BAD_REQUEST);
        }
    }



}
