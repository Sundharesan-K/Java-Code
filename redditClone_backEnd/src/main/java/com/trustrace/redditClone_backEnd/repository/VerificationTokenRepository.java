package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken,String> {
    Optional<VerificationToken> findByToken(String token);
}
