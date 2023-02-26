package com.trustrace.security30.service;

import com.trustrace.security30.configuration.UserUserDetails;
import com.trustrace.security30.pojo.User;
import com.trustrace.security30.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= Optional.ofNullable (userRepository.findByUserName (username));
        return user.map (UserUserDetails::new)
                .orElseThrow (()->new UsernameNotFoundException ("user not found " + username));
    }
}
