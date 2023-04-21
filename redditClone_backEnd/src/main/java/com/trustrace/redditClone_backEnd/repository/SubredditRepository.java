package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Subreddit;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubredditRepository extends MongoRepository<Subreddit,String> {
    Optional<Subreddit> findByName(String subredditName);
}
