package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote,Long> {
}
