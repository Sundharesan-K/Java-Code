package com.trustrace.mongodbSpringControl.controller;

import com.trustrace.mongodbSpringControl.Product.User;
import com.trustrace.mongodbSpringControl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
