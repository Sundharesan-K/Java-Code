package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Subreddit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubredditRepository extends MongoRepository<Subreddit,Long> {
}
