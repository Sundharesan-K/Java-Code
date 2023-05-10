package com.project.instagram2.O.controller.auth;

import com.project.instagram2.O.pojo.User;
import com.project.instagram2.O.pojo.auth.SignUp;
import com.project.instagram2.O.service.SignUpService;
import com.project.instagram2.O.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signUp")
public class SignUpController {
    @Autowired
    SignUpService signUpService;
    @Autowired
    UserService userService;

    @PostMapping
    public String signUp(@RequestBody SignUp signUp)throws Exception{
        userService.createUser(new User(signUp.getUserId(),signUp.getUserName()));
        signUpService.signUp(signUp);
        return "SignUp Process Done..";
    }

}
