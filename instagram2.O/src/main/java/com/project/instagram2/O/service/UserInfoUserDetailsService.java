package com.project.instagram2.O.service;

import com.project.instagram2.O.config.UserInfoUserDetails;
import com.project.instagram2.O.pojo.auth.SignUp;
import com.project.instagram2.O.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    SignUpRepository signUpRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<SignUp> signUpOptional = Optional.ofNullable(signUpRepository.getUserByUserName(userName));
        return signUpOptional.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found "+userName));
    }
}
