package com.trustrace.javaspring.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("studentList")
public class Student {
    @Id
    private String id;
    private String name;
    private int age;
    private String department;
    private String dob;
    private String hobbies;

    public Student(String id, String name, int age, String department, String dob, String hobbies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.dob = dob;
        this.hobbies=hobbies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}
