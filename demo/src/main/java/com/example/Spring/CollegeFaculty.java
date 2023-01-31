package com.example.Spring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facultyCollection")
public class CollegeFaculty {
    @Id
    private String id;
    private String name;
    private String department;
    private String email;
    private String phoneNumber;

    public CollegeFaculty(String facultyId, String name, String department, String email, String phoneNumber) {
        this.id = facultyId;
        this.name = name;
        this.department = department;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFacultyId() {
        return id;
    }

    public void setFacultyId(String facultyId) {
        this.id = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
