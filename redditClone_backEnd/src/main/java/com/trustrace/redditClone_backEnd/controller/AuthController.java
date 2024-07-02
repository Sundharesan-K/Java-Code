package com.trustrace.redditClone_backEnd.controller;

import com.trustrace.redditClone_backEnd.Service.AuthService;
import com.trustrace.redditClone_backEnd.dto.AuthenticationResponse;
import com.trustrace.redditClone_backEnd.dto.LoginRequest;
import com.trustrace.redditClone_backEnd.dto.RegisterRequest;
import com.trustrace.redditClone_backEnd.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static com.trustrace.redditClone_backEnd.response_message.Response.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    public static final String SIGNUP_PROCESS = "signup process";
    public static final String SIGN_UP = "signUp";
    private final AuthService authService;
    @PostMapping("/signUp")
    public ResponseEntity<APIResponse> signup(@RequestBody RegisterRequest registerRequest) {
        if (Objects.nonNull (registerRequest)) {
            authService.signUp (registerRequest);
        }
        return ResponseEntity.created (URI.create ("")).body (
                APIResponse.builder ()
                        .timeStamp (LocalDateTime.now ().toString ())
                        .data (Map.of (SIGN_UP,registerRequest))
                        .message (SUCCESS.getMessage())
                        .developerMessage (SIGNUP_PROCESS)
                        .status (HttpStatus.CREATED)
                        .statusCode (HttpStatus.CREATED.value ())
                        .build ()
        );
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
