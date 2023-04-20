package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Post;
import com.trustrace.redditClone_backEnd.model.Subreddit;
import com.trustrace.redditClone_backEnd.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post,String> {

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
