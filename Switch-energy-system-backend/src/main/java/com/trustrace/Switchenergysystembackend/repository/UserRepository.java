package com.trustrace.Switchenergysystembackend.repository;

import com.trustrace.Switchenergysystembackend.entity.User;
import com.trustrace.Switchenergysystembackend.entity.UserLogin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

   @Query(value = "{}",fields = "{password:0}")
    Optional<UserLogin> findByName(String name);
    List<User> findAll();

}
