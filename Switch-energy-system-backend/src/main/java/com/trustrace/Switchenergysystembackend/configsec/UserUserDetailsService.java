package com.trustrace.Switchenergysystembackend.configsec;

import com.trustrace.Switchenergysystembackend.entity.UserLogin;
import com.trustrace.Switchenergysystembackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserLogin> userUserDetailsService =userRepository.findByName (username);
      return   userUserDetailsService.map (UserUserDetails::new).orElseThrow (()-> new UsernameNotFoundException ("User not found"+username));

    }
}
