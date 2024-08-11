package com.secure.spring_secure_6.repo;

import com.secure.spring_secure_6.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

  User findByUsername(String username);
}
