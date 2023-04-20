package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Post;
import com.trustrace.redditClone_backEnd.model.User;
import com.trustrace.redditClone_backEnd.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VoteRepository extends MongoRepository<Vote,String> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
