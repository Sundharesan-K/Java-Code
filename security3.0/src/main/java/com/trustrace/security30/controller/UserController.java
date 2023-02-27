package com.trustrace.security30.controller;

import com.trustrace.security30.JwtTokenUtility.JwtService;
import com.trustrace.security30.dto.APIResponse;
import com.trustrace.security30.pojo.User;
import com.trustrace.security30.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/signUp")
    public ResponseEntity<APIResponse> createUser(@Validated @RequestBody User user){
        APIResponse response=new APIResponse ();
        response.setStatus ("Success");
        try{
            response.setData (userService.createUser(user));
        }catch (RuntimeException e){
            response.setMessage (e.getMessage ());
        }
        return new ResponseEntity<> (response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers(){
        return userService.getAllUser();
   }



}
