package com.trustrace.Switchenergysystembackend.repository;

import com.trustrace.Switchenergysystembackend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query(value = "{}",fields = "{password:0}")
    User findByEmailId(String emailId);
    List<User> findAll();

}
