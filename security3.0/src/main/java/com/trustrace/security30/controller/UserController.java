package com.trustrace.security30.controller;

import com.trustrace.security30.pojo.User;
import com.trustrace.security30.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public String  CreateUser(@RequestBody User user){
        return userService.CreateUser(user);
    }
}
