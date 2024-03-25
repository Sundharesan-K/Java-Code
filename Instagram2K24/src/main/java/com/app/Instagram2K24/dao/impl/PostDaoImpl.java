package com.app.Instagram2K24.dao.impl;

import com.app.Instagram2K24.dao.PostDao;
import com.app.Instagram2K24.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostDaoImpl implements PostDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public void addPost(Post post1) {
        mongoTemplate.save(post1);
    }
}
