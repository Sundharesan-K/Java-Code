package com.trustrace.javaspring.service;

import com.trustrace.javaspring.pojo.Student;
import com.trustrace.javaspring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository repository;

    public Student CreateStudent(Student student) {
        return repository.CreateStudent(student);
    }

    public List<Student> getAllStudent() {
        return repository.getAllStudent();
    }
}
