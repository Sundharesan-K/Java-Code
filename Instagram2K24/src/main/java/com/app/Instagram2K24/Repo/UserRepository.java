package com.app.Instagram2K24.Repo;

import com.app.Instagram2K24.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserProfile,String> {

}
