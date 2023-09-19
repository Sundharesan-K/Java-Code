package com.classroom_api.project.repository;

import com.classroom_api.project.entity.QuestionAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends MongoRepository<QuestionAnswer,String> {
}
