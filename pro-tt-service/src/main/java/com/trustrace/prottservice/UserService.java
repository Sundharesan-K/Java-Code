package com.trustrace.prottservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Object createUser(User user) {
        if (user.equals (userRepository)){
            userRepository.createUser(user);
        } else if (user.equals (null)) {
            return "This User is Already Exists";
        }
        return user;
    }
}
