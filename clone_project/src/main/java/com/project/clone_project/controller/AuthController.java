package com.project.clone_project.controller;

import com.project.clone_project.dto.AuthenticationResponse;
import com.project.clone_project.dto.LoginRequest;
import com.project.clone_project.dto.RefreshTokenRequest;
import com.project.clone_project.dto.RegisterRequest;
import com.project.clone_project.service.AuthService;
import com.project.clone_project.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sun.mail.iap.Response.OK;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/register")
    public ResponseEntity<String> singUp(@RequestBody RegisterRequest request){
        authService.singUp(request);
        return new ResponseEntity<>("User Registration Successfully", HttpStatus.OK);
    }
    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable("token") String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activate Successfully",HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse loginUser(@RequestBody LoginRequest loginRequest){
        return authService.loginUser(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest tokenRequest){
        return authService.refreshToken(tokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully");
    }
}
