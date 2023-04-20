package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Comment;
import com.trustrace.redditClone_backEnd.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByPost(Post post);
}
