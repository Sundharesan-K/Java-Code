package com.classroom_api.project.entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quiz_answer")
public class QuizAnswer {
    @Id
    private String id;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submtionDate;

    private Boolean finish;

    @ManyToOne
    private Quiz quiz;

    @OneToMany
    private List<QuestionAnswer> questionAnswers;
}
