package com.project.instagram2.O.repository;

import com.project.instagram2.O.pojo.auth.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignUpRepository implements QueryImpl{
    @Autowired
    MongoTemplate mongoTemplate;

    public SignUp getUserByUserName(String userName) {
        return mongoTemplate.findOne(getQueryForUserName(userName), SignUp.class);
    }

    public SignUp getUserByEmail(String email) {
        return mongoTemplate.findOne(getQueryForEmail(email),SignUp.class);
    }

    public void signUp(SignUp signUp) {
        mongoTemplate.save(signUp);
    }
}
