package com.project.clone_project.repository;

import com.project.clone_project.model.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepo extends MongoRepository<VerificationToken,String> {
    Optional<VerificationToken> findByToken(String token);
}
