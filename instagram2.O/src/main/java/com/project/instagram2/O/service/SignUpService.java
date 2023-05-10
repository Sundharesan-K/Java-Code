package com.project.instagram2.O.service;

import com.project.instagram2.O.pojo.auth.SignUp;
import com.project.instagram2.O.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    SignUpRepository signUpRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public void signUp(SignUp signUp) {
        signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
        System.out.println(signUp.getPassword());
        signUpRepository.signUp(signUp);
    }
}
