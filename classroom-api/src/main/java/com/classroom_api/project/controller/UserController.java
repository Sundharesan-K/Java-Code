package com.classroom_api.project.controller;

import com.classroom_api.project.model.user.UserResponseDTO;
import com.classroom_api.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String id){
        UserResponseDTO user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/userName")
    public ResponseEntity<List<UserResponseDTO>> findUsersByName(@RequestParam String userName){
        List<UserResponseDTO> user = userService.getUsersByName(userName);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponseDTO> findUserByEmail(@RequestParam String email){
        UserResponseDTO user = userService.findUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
