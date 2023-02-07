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
    @GetMapping("/{id}")
    public ResponseEntity<Student> getByStudentId(@PathVariable("id") String id){
        Student student2=service.getByStudentId(id);
        if (student2==null){
            return new ResponseEntity<> (null,HttpStatus.NOT_ACCEPTABLE);
        }else {
            return new ResponseEntity<> (student2,HttpStatus.ACCEPTED);
        }
    }
    @GetMapping("{name}")
    public Student getByStudentName(@PathVariable("name") String name){
        return service.getByStudentName(name);
    }
    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student){
        return service.updateStudent(student);
    }
    @DeleteMapping("/delete")
    public String deleteStudent(@RequestBody Student student){
        return service.deleteStudent(student);
    }

}
