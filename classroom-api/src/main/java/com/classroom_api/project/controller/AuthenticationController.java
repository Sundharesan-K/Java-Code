package com.classroom_api.project.controller;

import com.classroom_api.project.model.auth.AuthenticationRequest;
import com.classroom_api.project.model.auth.AuthenticationResponse;
import com.classroom_api.project.model.user.UserRequestDTO;
import com.classroom_api.project.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRequestDTO userRequestDTO){
//        AuthenticationResponse response = authenticationService.register(userRequestDTO);
//        return ResponseEntity.ok(response);
        authenticationService.register(userRequestDTO);
        return ResponseEntity.ok(HttpStatus.CREATED.toString());
    }

    @PostMapping("/Authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}
