package com.trustrace.javaspring.controller;

import com.trustrace.javaspring.pojo.Student;
import com.trustrace.javaspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService service;

    @PostMapping("/")
    public ResponseEntity<Student> CreateStudent(@RequestBody Student student){
        Student student1=service.CreateStudent(student);
        if (student1==null){
            return new ResponseEntity<> (null,HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<> (student1,HttpStatus.CREATED);
        }
    }
    @GetMapping("/")
    public List<Student> getAllStudent(){
        return service.getAllStudent ();
    }
}
