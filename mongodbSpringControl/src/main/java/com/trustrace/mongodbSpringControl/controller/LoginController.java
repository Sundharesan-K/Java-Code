package com.trustrace.mongodbSpringControl.controller;

import com.trustrace.mongodbSpringControl.JwtResponse;
import com.trustrace.mongodbSpringControl.Login;
import com.trustrace.mongodbSpringControl.service.JwtService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    @PostMapping("/login")
    public JwtResponse login(@RequestBody Login login){
        Authentication authentication=manager.authenticate (new UsernamePasswordAuthenticationToken (login.getEmailId ()
        ,login.getPassword ()));
        if (authentication.isAuthenticated ()){
            String token= jwtService.generateToken (login.getEmailId ());
            return new JwtResponse (token);
        }else {
            throw new UsernameNotFoundException ("Invalid user request ! !");
        }
    }
}
