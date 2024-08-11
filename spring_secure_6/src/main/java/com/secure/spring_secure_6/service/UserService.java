package com.secure.spring_secure_6.service;

import com.secure.spring_secure_6.model.User;
import com.secure.spring_secure_6.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JWTService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;

  public User register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepo.save(user);
  }

  public String verify(User user) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(user.getUsername());
    } else {
      return "fail";
    }
  }
}
