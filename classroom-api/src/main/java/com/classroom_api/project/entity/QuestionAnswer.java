package com.classroom_api.project.entity;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "question_answer")
public class QuestionAnswer {
    @Id
    private String id;

    @ManyToOne
    private Question question;

    private String answer;

    public QuestionAnswer(Question question, String answer) {
        this.question=question;
        this.answer=answer;
    }
}
