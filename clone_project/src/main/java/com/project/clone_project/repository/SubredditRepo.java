package com.project.clone_project.repository;

import com.project.clone_project.model.Subreddit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepo extends MongoRepository<Subreddit,String> {
    Optional<Subreddit> findByName(String subredditName);
}
