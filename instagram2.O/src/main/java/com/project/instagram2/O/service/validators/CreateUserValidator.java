package com.project.instagram2.O.service.validators;

import com.project.instagram2.O.pojo.User;
import com.project.instagram2.O.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserValidator {
    @Autowired
    UserRepository userRepository;

    public void perValidateUser(User user)throws Exception{
        if (isIdAvailable(user.getUserId())){
            throw new Exception("User with instagram id "+user.getUserId() + " already Exists");
        }else if(isNameAvailable(user.getUserName())) {
            throw new Exception("User with instagram id " + user.getUserName() + " already exists");
        }
    }

    public Boolean isIdAvailable(String userId) { return (userRepository.getUserById(userId) != null); }
    public Boolean isNameAvailable(String userName) { return (userRepository.getUserByName(userName) != null); }
}
