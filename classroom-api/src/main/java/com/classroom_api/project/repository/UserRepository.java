package com.classroom_api.project.repository;

import com.classroom_api.project.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>  {

    Optional<User> findByEmail(String email);

    Optional<List<User>> findByUserName(String userName);
}
