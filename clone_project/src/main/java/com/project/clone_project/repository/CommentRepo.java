package com.project.clone_project.repository;

import com.project.clone_project.model.Comment;
import com.project.clone_project.model.Post;
import com.project.clone_project.model.User;
import jakarta.validation.constraints.Null;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends MongoRepository<Comment,String > {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
