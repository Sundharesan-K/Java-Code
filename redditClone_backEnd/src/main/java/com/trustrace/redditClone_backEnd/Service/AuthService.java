package com.trustrace.redditClone_backEnd.Service;

import com.trustrace.redditClone_backEnd.dto.AuthenticationResponse;
import com.trustrace.redditClone_backEnd.dto.LoginRequest;
import com.trustrace.redditClone_backEnd.dto.RegisterRequest;
import com.trustrace.redditClone_backEnd.exceptions.SpringRedditException;
import com.trustrace.redditClone_backEnd.exceptions.UserException;
import com.trustrace.redditClone_backEnd.model.NotificationEmail;
import com.trustrace.redditClone_backEnd.model.User;
import com.trustrace.redditClone_backEnd.model.VerificationToken;
import com.trustrace.redditClone_backEnd.repository.UserRepository;
import com.trustrace.redditClone_backEnd.repository.VerificationTokenRepository;
import com.trustrace.redditClone_backEnd.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final JwtProvider jwtProvider;

    public void signUp(RegisterRequest registerRequest) {
        log.info ("SignUp Process");
        if (userRepository.findByEmail (registerRequest.getEmail ()).isPresent ()) {
            throw new RuntimeException ("Email Already exists");
        }
        User user = new User ();
        user.setUsername (registerRequest.getUsername ());
        user.setEmail (registerRequest.getEmail ());
        user.setPassword (passwordEncoder.encode (registerRequest.getPassword ()));
        user.setCreated (Instant.now ());
        user.setEnabled (false);
        userRepository.save (user);
        String token = generateVerificationToken (user);
        mailService.sendMail (new NotificationEmail ("Please Activate your account ",
                user.getEmail (), "Thank you for signing up to Spring Reddit," + " please click on the below " +
                "url activate your account : " +
                "http://localhost:9999/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        log.info ("generateToken process");
        String token = UUID.randomUUID ().toString ();
        VerificationToken verificationToken = new VerificationToken ();
        verificationToken.setToken (token);
        verificationToken.setUser (user);
        verificationTokenRepository.save (verificationToken);
        return token;
    }

    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.
                getContext ().getAuthentication ().getPrincipal ();
        return userRepository.findByUsername (principal.getSubject ())
                .orElseThrow (() -> new UsernameNotFoundException ("User name not found - " + principal.getSubject ()));
    }

    public void verifyAccount(String token) {
        log.info ("VerificationToken Process");
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken (token);
        verificationToken.orElseThrow (() -> new SpringRedditException ("InvalidToken"));
        fetchUserAndEnable (verificationToken.get ());
    }

    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser ().getUsername ();
        User user = userRepository.findByUsername (username).orElseThrow (() -> new SpringRedditException ("User Not Found with name - " + username));
        user.setEnabled (true);
        userRepository.save (user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        log.info ("login process");
        Authentication authenticate = authenticationManager.authenticate (new UsernamePasswordAuthenticationToken (loginRequest.getUsername ()
                , loginRequest.getPassword ()));
        SecurityContextHolder.getContext ().setAuthentication (authenticate);
        String token = jwtProvider.generateToken (authenticate);
        return new AuthenticationResponse (token, loginRequest.getUsername ());
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated ();
    }

}
