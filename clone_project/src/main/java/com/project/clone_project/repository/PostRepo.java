package com.project.clone_project.repository;

import com.project.clone_project.dto.PostResponse;
import com.project.clone_project.model.Post;
import com.project.clone_project.model.Subreddit;
import com.project.clone_project.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends MongoRepository<Post,String> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findPostByUser(User user);

}
