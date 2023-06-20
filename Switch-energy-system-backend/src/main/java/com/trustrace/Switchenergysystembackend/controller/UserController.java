package com.trustrace.Switchenergysystembackend.controller;

import com.trustrace.Switchenergysystembackend.controller.response.APIResponse;
import com.trustrace.Switchenergysystembackend.entity.AuthRequest;
import com.trustrace.Switchenergysystembackend.entity.User;
import com.trustrace.Switchenergysystembackend.entity.UserLogin;
import com.trustrace.Switchenergysystembackend.security.JwtUtility;
import com.trustrace.Switchenergysystembackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtility jwtUtility;

//    @PostMapping("/new")
//    public String addNewUser(@RequestBody UserLogin userLogin) {
//        return userService.addUser (userLogin);
//    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createUser( @RequestBody User user) {
        APIResponse response = new APIResponse ();
        response.setStatus ("Success");
        try {
            response.setData (userService.createUser (user));
        } catch (RuntimeException e) {
            response.setMessage (e.getMessage ());
        }
        return new ResponseEntity<> (response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@Valid @RequestBody UserLogin userLogin) {
        APIResponse response = new APIResponse ();
        HttpHeaders responseHeader = new HttpHeaders ();
        try {
            response.setData (userService.login (userLogin));
            String jwtToken = jwtUtility.generateToken (userLogin.getName (), 10 * 60 * 60);
            responseHeader.set ("JWTToken", jwtToken);
        } catch (Exception e) {
            response.setMessage (e.getMessage ());
            return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<> (response, responseHeader, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllUser(@RequestHeader(value = "authorization") String auth) {
        APIResponse response = new APIResponse ();
        if (Objects.equals (jwtUtility.validateToken (auth), "admin")) {
            response.setStatus ("Success");
            response.setData (userService.getAllUser ());
            response.setMessage ("User data Successfully");
            return new ResponseEntity<> (response, HttpStatus.OK);
        }
        response.setMessage ("No access");
        return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return jwtUtility.generateToken (authRequest.getUserName (), 10 * 60 * 60);
    }
}