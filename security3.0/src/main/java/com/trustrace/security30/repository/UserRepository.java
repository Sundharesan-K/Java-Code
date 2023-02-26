package com.trustrace.security30.repository;

import com.trustrace.security30.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate template;

    public void save(User user) {
        template.save (user);
    }

    public User findByUserName(String username) {
        User user = template.findOne (Query.query (Criteria.where ("username").is (username)),User.class);
        return user;
    }
}
