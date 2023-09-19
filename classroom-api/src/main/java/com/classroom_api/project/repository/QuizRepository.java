package com.classroom_api.project.repository;

import com.classroom_api.project.entity.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QuizRepository extends MongoRepository<Quiz,String> {
}
