package com.trustrace.DetailsSpringBoot.controller;

import com.trustrace.DetailsSpringBoot.pojo.User;
import com.trustrace.DetailsSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public  ResponseEntity<User> CreateUser(@RequestBody User user ){
        User user1;
        user1 = userService.CreateUser(user);
        if (user!=null){
            return new ResponseEntity<> (user1, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<> (null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users=userService.getAllUser();
        if (users==null||users.isEmpty ()){
            return new ResponseEntity<> (null,HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<> (users,HttpStatus.CREATED);
        }
    }
    @GetMapping("/{id}/")
    public ResponseEntity<User> getByUserId(@PathVariable("id") String id){
        User user2=userService.getByUserId(id);
        if (user2==null){
            return new ResponseEntity<> (null,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<> (user2,HttpStatus.OK);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        User user2=userService.save(user);
        if (user2==null){
            return new ResponseEntity<> (null,HttpStatus.NOT_MODIFIED);
        }else {
            return new ResponseEntity<> (user2,HttpStatus.ACCEPTED);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserId(@PathVariable("id") String id){
        String user=userService.deleteUserId(id);
        if (user==null){
            return new ResponseEntity<> (null,HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<> (user,HttpStatus.LOOP_DETECTED);
        }
    }
}
