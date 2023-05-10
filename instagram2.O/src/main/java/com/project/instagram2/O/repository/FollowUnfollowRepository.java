package com.project.instagram2.O.repository;

import com.project.instagram2.O.pojo.Followers;
import com.project.instagram2.O.pojo.Following;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class FollowUnfollowRepository implements QueryImpl{
    @Autowired
    MongoTemplate mongoTemplate;

    public List<String> getFollowingList(String userId) {
        return mongoTemplate.findOne(getQueryForUserId(userId), Following.class).getFollowingList();
    }

    public List<String> getFollowersList(String userId) {
        return mongoTemplate.findOne(getQueryForUserId(userId), Followers.class).getFollowersList();
    }
}
