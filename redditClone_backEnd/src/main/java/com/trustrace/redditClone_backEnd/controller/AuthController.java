package com.trustrace.redditClone_backEnd.controller;

import com.trustrace.redditClone_backEnd.Service.AuthService;
import com.trustrace.redditClone_backEnd.dto.AuthenticationResponse;
import com.trustrace.redditClone_backEnd.dto.LoginRequest;
import com.trustrace.redditClone_backEnd.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signUp")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signUp(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<> ("Account Activated SuccessFully",HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
       return authService.login(loginRequest);
    }
}
