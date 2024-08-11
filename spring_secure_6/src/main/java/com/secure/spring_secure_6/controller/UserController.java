package com.secure.spring_secure_6.controller;

import com.secure.spring_secure_6.model.User;
import com.secure.spring_secure_6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/add-user")
  public User register(@RequestBody User user) {
    return userService.register(user);
  }

  @PostMapping("/login")
  public String login(@RequestBody User user) {
    return userService.verify(user);
  }
}
