package com.project.clone_project.repository;

import com.project.clone_project.model.Post;
import com.project.clone_project.model.User;
import com.project.clone_project.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepo extends MongoRepository<Vote,String> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User user);
}
