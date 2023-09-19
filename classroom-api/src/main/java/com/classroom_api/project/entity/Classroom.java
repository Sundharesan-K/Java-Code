package com.classroom_api.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "classrooms")
public class Classroom {
    @Id
    private String id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Quiz> quizzes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> members;

   public Classroom(String name){
        this.name = name;
    }

    public void addMember(User user){
        if (members == null){
            members = new ArrayList<>();
        }
        members.add(user);
    }
}
