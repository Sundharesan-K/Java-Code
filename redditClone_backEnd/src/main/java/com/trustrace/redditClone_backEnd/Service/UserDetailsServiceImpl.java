package com.trustrace.redditClone_backEnd.Service;

import com.trustrace.redditClone_backEnd.model.User;
import com.trustrace.redditClone_backEnd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional
                .orElseThrow(()->new UsernameNotFoundException("No User Found with Username :"+username));
        return new  org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),user.isEnabled(),true,true,true,getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return singletonList(new SimpleGrantedAuthority("USER"));
    }
}
