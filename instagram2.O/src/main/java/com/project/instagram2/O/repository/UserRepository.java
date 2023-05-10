package com.project.instagram2.O.repository;

import com.project.instagram2.O.pojo.Followers;
import com.project.instagram2.O.pojo.Following;
import com.project.instagram2.O.pojo.User;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public class UserRepository implements QueryImpl{
    @Autowired
    MongoTemplate mongoTemplate;

    public User getUserById(String userId) {
        return mongoTemplate.findOne(getQueryForUserId(userId),User.class);
    }

    public User getUserByName(String userName) {
        return mongoTemplate.findOne(getQueryForUserName(userName),User.class);
    }

    public void createUser(User user) {
        mongoTemplate.save(user);
        mongoTemplate.save(new Following(user.getUserId()));
        mongoTemplate.save(new Followers(user.getUserId()));
    }

    public List<User> getAllUsers() {
       return mongoTemplate.findAll(User.class);
    }

    public void editBio(String userId, String bio) {
        mongoTemplate.findAndModify(getQueryForUserId(userId),new Update().set("bio",bio),User.class);
    }

    public void updateProfilePicture(String userId, MultipartFile multipartFile)throws Exception {
        Update update = new Update();
        update.set("profilePicture",new Binary(BsonBinarySubType.BINARY,multipartFile.getBytes()));
        mongoTemplate.findAndModify(getQueryForUserId(userId),update,User.class);
    }

    public Binary getProfilePicture(String userId) {
        System.out.println (getUserById (userId));
        System.out.println (userId);
        return getUserById (userId).getProfilePicture ();
    }

    public void deleteUser(String userId) {
        mongoTemplate.remove (userId);
    }
}
