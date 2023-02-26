package com.trustrace.security30.service;

import com.trustrace.security30.pojo.User;
import com.trustrace.security30.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public String CreateUser(User user){
        user.setPassword (passwordEncoder.encode (user.getPassword ()));
        userRepository.save (user);
        return "User added SuccessFully";
    }
}
