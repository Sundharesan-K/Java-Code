package com.project.instagram2.O.service;

import com.project.instagram2.O.pojo.User;
import com.project.instagram2.O.repository.UserRepository;
import com.project.instagram2.O.service.validators.CreateUserValidator;
import com.project.instagram2.O.service.validators.IdNameAndEmailValidator;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IdNameAndEmailValidator idNameAndEmailValidator;
    @Autowired
    private CreateUserValidator createUserValidator;
    public void createUser(User user)throws Exception {
        createUserValidator.perValidateUser(user);
        userRepository.createUser(user);
    }

    public User getUserById(String userId)throws Exception{
        idNameAndEmailValidator.preValidateUserId(userId);
        return userRepository.getUserById(userId);
    }

    public User getUserByUserName(String userName)throws Exception {
        idNameAndEmailValidator.preValidateUserName(userName);
        return userRepository.getUserByName(userName);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void editBio(String userId, String bio)throws Exception {
        idNameAndEmailValidator.isIdExist(userId);
        userRepository.editBio(userId,bio);
    }

    public void updateProfilePicture(String userId, MultipartFile multipartFile) throws Exception {
        userRepository.updateProfilePicture(userId,multipartFile);
    }

    public Binary getProfilePicture(String userId)throws Exception {
       Binary obj = userRepository.getProfilePicture(userId);
        System.out.println (obj);
        if (obj == null){
            throw new Exception ("Profile Picture is Empty...try to upload profile Picture");
        }
        return obj;
    }

    public void deleteUser(String userId)throws Exception {
        userRepository.deleteUser(userId);
    }
}
