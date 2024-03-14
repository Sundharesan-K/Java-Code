package com.app.Instagram2K24.dao.impl;

import com.app.Instagram2K24.dao.UserDao;
import com.app.Instagram2K24.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final MongoTemplate mongoTemplate;

    @Override
    public UserProfile findUserFromEmailId(String emailId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("emailId").is(emailId));
        return mongoTemplate.findOne(query, UserProfile.class);
    }

    @Override
    public void addUser(UserProfile userProfiler) {
        mongoTemplate.save(userProfiler);
    }

    @Override
    public UserProfile findUserFromUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, UserProfile.class);
    }

    @Override
    public void UsersAdd(List<UserProfile> list) {
        mongoTemplate.save(list);
    }
}
