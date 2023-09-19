package com.classroom_api.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "quizzes")
public class Quiz {
    @Id
    private String id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuizAnswer> quizAnswerList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    public List<Question> getQuestions() {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        return questions;
    }
}
