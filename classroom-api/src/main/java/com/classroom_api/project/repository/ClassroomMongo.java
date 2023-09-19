package com.classroom_api.project.repository;

import com.classroom_api.project.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassroomMongo {
    private final MongoTemplate mongoTemplate;
    public List<Classroom> getAllMembersEmail(List<String> email){
        var query = new Query();
        query.addCriteria(Criteria.where("members").in(email));
        return mongoTemplate.find(query, Classroom.class);
    }
}
