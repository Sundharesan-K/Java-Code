package com.trustrace.mongodbSpringControl.service;

import com.trustrace.mongodbSpringControl.Product.User;
import com.trustrace.mongodbSpringControl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String createUser(User user) {
        user.setPassword (passwordEncoder.encode (user.getPassword ()));
        userRepository.save (user);
        return "User Added to System;";
    }
}
