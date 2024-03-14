package com.app.Instagram2K24.dao.impl;

import com.app.Instagram2K24.dao.FollowDao;
import com.app.Instagram2K24.model.Follower;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowDaoImpl implements FollowDao {
    private final MongoTemplate mongoTemplate;
    @Override
    public void addFollow(Follower follower) {
        mongoTemplate.save(follower);
    }
}
