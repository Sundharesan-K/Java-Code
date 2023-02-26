package com.trustrace.security30.controller;

import com.trustrace.security30.JwtTokenUtility.JwtService;
import com.trustrace.security30.pojo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/login")
    public String login(@RequestBody Login login){
        Authentication authentication=manager.authenticate (new UsernamePasswordAuthenticationToken (login.getUsername (),login.getPassword ()));
        if (authentication.isAuthenticated ()){
            return jwtService.generateToken (login.getUsername ());
        }else {
            throw new UsernameNotFoundException ("Invalid user request ! !");
        }


    }

}
