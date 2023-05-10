package com.project.instagram2.O.repository;

import com.project.instagram2.O.pojo.auth.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class LoginRepository implements QueryImpl{
    @Autowired
    MongoTemplate mongoTemplate;

    public String login(String email) {
        return Objects.requireNonNull(mongoTemplate.findOne(getQueryForEmail(email),
                SignUp.class)).getPassword();
    }
}
