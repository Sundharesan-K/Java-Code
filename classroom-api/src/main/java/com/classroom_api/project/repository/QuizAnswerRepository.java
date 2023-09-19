package com.classroom_api.project.repository;

import com.classroom_api.project.entity.QuizAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAnswerRepository extends MongoRepository<QuizAnswer,String> {
}
