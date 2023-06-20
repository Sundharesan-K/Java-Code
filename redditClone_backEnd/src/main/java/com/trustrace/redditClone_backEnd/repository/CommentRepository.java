package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Comment;
import com.trustrace.redditClone_backEnd.model.Post;
import com.trustrace.redditClone_backEnd.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
