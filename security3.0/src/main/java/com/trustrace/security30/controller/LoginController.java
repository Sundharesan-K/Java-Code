package com.trustrace.security30.controller;

import com.trustrace.security30.JwtTokenUtility.JwtService;
import com.trustrace.security30.dto.APIResponse;
import com.trustrace.security30.dto.JwtResponse;
import com.trustrace.security30.pojo.Login;
import com.trustrace.security30.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody Login login){
        Authentication authentication=manager.authenticate (new UsernamePasswordAuthenticationToken (login.getUsername (),login.getPassword ()));
        if (authentication.isAuthenticated ()){
            String token= jwtService.generateToken (login.getUsername ());
            return new JwtResponse (token);
        }else {
            throw new UsernameNotFoundException ("Invalid user request ! !");
        }
    }




//    @PostMapping("/login")
//    public ResponseEntity<APIResponse> login (@Validated @RequestBody Login login ){
//        Authentication authentication=manager.authenticate (new UsernamePasswordAuthenticationToken (login.getUsername (),login.getPassword ()));
//        APIResponse response=new APIResponse ();
//        HttpHeaders httpHeaders=new HttpHeaders ();
//        if (authentication.isAuthenticated ()){
//            response.setData (service.login(login));
//            String jwtToken=jwtService.generateToken (login.getUsername ());
//        }
//        try {
//
//
//            httpHeaders.set ("JWTTOKEN",jwtToken);
//        }catch (Exception e){
//            response.setMessage (e.getMessage ());
//            return new ResponseEntity<> (response,httpHeaders, HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity<> (response,httpHeaders,HttpStatus.ACCEPTED);
//    }




    @GetMapping("/welcome")
    public String hello(){
        return "WELCOME !";
    }

}
