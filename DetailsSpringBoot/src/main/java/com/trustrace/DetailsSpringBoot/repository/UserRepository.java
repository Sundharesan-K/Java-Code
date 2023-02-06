package com.trustrace.DetailsSpringBoot.repository;

import com.trustrace.DetailsSpringBoot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    MongoTemplate template;

    public User CreateUser(User user) {
        return template.save (user);
    }

    public List<User> getAllUser() {
        return template.findAll (User.class);
    }

    public User getByUserId(String id) {
        return template.findById (id,User.class);
    }

    public User save(User user) {
        return template.save (user);
    }

    public String deleteUserId(String id) {
         template.remove (User.class);
         return "SUPER";
    }
}
