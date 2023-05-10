package com.project.instagram2.O.controller.auth;

import com.project.instagram2.O.dto.AuthRequest;
import com.project.instagram2.O.dto.JwtResponse;
import com.project.instagram2.O.pojo.auth.Login;
import com.project.instagram2.O.service.JwtService;
import com.project.instagram2.O.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public String login(@RequestBody Login login)throws Exception{
        loginService.login(login.getEmail(),login.getPassword());
        return "Logged in SuccessFully";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            String token = jwtService.generateToken(authRequest.getUserName());
            return new JwtResponse(token);
        }else {
            throw new UsernameNotFoundException("Invalid User Request");
        }
    }
}
