package com.project.instagram2.O.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LikeUnlikeRepository {
    @Autowired
    MongoTemplate mongoTemplate;

}
