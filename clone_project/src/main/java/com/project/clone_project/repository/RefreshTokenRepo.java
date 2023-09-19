package com.project.clone_project.repository;

import com.project.clone_project.model.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends MongoRepository<RefreshToken,String> {

    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String token);
}
