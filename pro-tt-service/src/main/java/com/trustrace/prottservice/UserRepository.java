package com.trustrace.prottservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    public void createUser(User user) {
        mongoTemplate.save (user);
    }
}
