package com.project.instagram2.O.repository;

import com.project.instagram2.O.pojo.Like;
import com.project.instagram2.O.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository implements QueryImpl{
    @Autowired
    MongoTemplate mongoTemplate;

    public Post getPostById(String postId) {
        return mongoTemplate.findOne(getQueryForPostId(postId),Post.class);
    }

    public void createPost(String userId, String imageUrl) {
        Post newPost = new Post (userId,imageUrl);
        mongoTemplate.save (newPost);
        mongoTemplate.save (new Like (newPost.getPostId ()));
    }
}
