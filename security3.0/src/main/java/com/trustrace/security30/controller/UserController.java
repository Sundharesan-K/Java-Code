package com.trustrace.security30.controller;

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
import java.util.Objects;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/signUp")
//    public String  CreateUser(@RequestBody User user){
//        return userService.CreateUser(user);
//    }

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
//    @PreAuthorize ("hasRole('ADMIN')")
    public List<User> getAllUsers(){
//        APIResponse response=new APIResponse ();
//        response.setStatus ("Success");
//        try {
//            response.setData (userService.getAllUser(user));
//            response.setMessage ("Users data fetched Successfully");
//        }catch (Exception e){
//            response.setMessage (e.getMessage ());
//            return new ResponseEntity<> (response,HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity<> (response,HttpStatus.ACCEPTED);
//
        return userService.getAllUser();

   }
}
