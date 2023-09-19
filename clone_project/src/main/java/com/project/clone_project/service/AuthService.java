package com.project.clone_project.service;

import com.project.clone_project.dto.*;
import com.project.clone_project.exception.EmailAlreadyExistsException;
import com.project.clone_project.exception.SpringRedditException;
import com.project.clone_project.model.User;
import com.project.clone_project.model.VerificationToken;
import com.project.clone_project.repository.UserRepo;
import com.project.clone_project.repository.VerificationTokenRepo;
import com.project.clone_project.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final UserRepo userRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    public void singUp(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("This "+request.getEmail()+" email already register, please register different email");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(),"Thank you for signing up to Spring Reddit,"+
                "please click on the below url link to activate your account :" +
                "http://localhost:8000/api/auth/accountVerification/"+token));

    }
    public User getCurrentUser(){
        Jwt principal = (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return userRepo.findByEmail(principal.getSubject())
                .orElseThrow(()->new UsernameNotFoundException("Not Found -"+principal.getSubject()));
    }

    private String generateVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken();
        String token = UUID.randomUUID().toString();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepo.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token).orElseThrow(()->new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken);
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepo.findByEmail(email).orElseThrow(()->new SpringRedditException("User not found with email "+email));
        user.setEnabled(true);
        verificationToken.getUser().setEnabled(true);
        verificationTokenRepo.save(verificationToken);
        userRepo.save(user);
    }

    public AuthenticationResponse loginUser(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .email(loginRequest.getEmail())
                .build();

    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest tokenRequest) {
        refreshTokenService.validateRefreshToken(tokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(tokenRequest.getEmail());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(tokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .email(tokenRequest.getEmail())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated ();
    }
}
