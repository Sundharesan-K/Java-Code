package com.classroom_api.project.repository;

import com.classroom_api.project.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question,String> {
}
