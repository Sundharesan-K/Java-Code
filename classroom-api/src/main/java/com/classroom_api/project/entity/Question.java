package com.classroom_api.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "questions")
public class Question {
    @Id
    private String id;

    private String text;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String correctAnswers;
}
