package com.trustrace.mongodbSpringControl.service;

import com.trustrace.mongodbSpringControl.Product.User;
import com.trustrace.mongodbSpringControl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
       Optional<User> user=userRepository.findByEmailId(emailId);
     return user.map (UserUserDetails::new)
               .orElseThrow (()->new UsernameNotFoundException (emailId + "user not found"));
    }
}
