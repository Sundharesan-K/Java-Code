package com.project.instagram2.O.controller;

import com.project.instagram2.O.pojo.User;
import com.project.instagram2.O.service.UserService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/id/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User getUserById(@PathVariable String userId)throws Exception{
        return userService.getUserById(userId);
    }

    @GetMapping("/userName/{userName}")
    public User getUserByUserName(@PathVariable String userName)throws Exception{
        return userService.getUserByUserName(userName);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
       return userService.getAllUsers();
    }

    @PutMapping("{userId}/editBio/{bio}")
    public String editBio(@PathVariable String userId, @PathVariable String bio)throws Exception{
        userService.editBio(userId,bio);
        return "The altered bio is "+bio;
    }

    @PostMapping("{userId}/uploadImage")
    public void updateProfilePicture(@PathVariable String userId, @RequestParam("file") MultipartFile multipartFile)throws Exception{
        userService.updateProfilePicture(userId,multipartFile);
    }

    @GetMapping("/{userId}/getImage")
    public Binary getProfilePicture(@PathVariable String userId)throws Exception{
        return userService.getProfilePicture(userId);
    }

    @DeleteMapping("/id/{userId}")
    public String deleteUser(@PathVariable String userId)throws Exception{
        userService.deleteUser(userId);
        return "User Deleted Successfully";
    }

}
