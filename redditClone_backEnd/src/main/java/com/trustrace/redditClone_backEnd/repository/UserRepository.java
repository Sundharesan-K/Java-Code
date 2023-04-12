package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
}
