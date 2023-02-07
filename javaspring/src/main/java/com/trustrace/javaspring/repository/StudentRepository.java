package com.trustrace.javaspring.repository;

import com.trustrace.javaspring.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.expression.spel.ast.Selection;
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

    public Student getByStudentId(String id) {
        return template.findById (id,Student.class);
    }

    public Student getByStudentName(String name) {
        return template.findOne(Query.query (Criteria.where ("name").is (name)),Student.class);
    }

    public String deleteStudent(Student student) {
        template.remove (student);
        return "DELETE SUCCESSFULLY";
    }
}
