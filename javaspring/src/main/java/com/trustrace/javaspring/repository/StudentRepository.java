package com.trustrace.javaspring.repository;

import com.trustrace.javaspring.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    MongoTemplate template;

    public Student CreateStudent(Student student) {
        return template.save (student);
    }

    public List<Student> getAllStudent() {
        return template.findAll (Student.class);
    }
}
