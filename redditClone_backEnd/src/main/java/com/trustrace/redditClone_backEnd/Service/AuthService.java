package com.trustrace.redditClone_backEnd.Service;

import com.trustrace.redditClone_backEnd.dto.LoginRequest;
import com.trustrace.redditClone_backEnd.dto.RegisterRequest;
import com.trustrace.redditClone_backEnd.exceptions.SpringRedditException;
import com.trustrace.redditClone_backEnd.model.NotificationEmail;
import com.trustrace.redditClone_backEnd.model.User;
import com.trustrace.redditClone_backEnd.model.VerificationToken;
import com.trustrace.redditClone_backEnd.repository.UserRepository;
import com.trustrace.redditClone_backEnd.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    @Transactional
    public void signUp(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepository.save(user);
       String token = generateVerificationToken(user);
       mailService.sendMail(new NotificationEmail("Please Activate your account ",
               user.getEmail(),"Thank you for signing up to Spring Reddit,"+" please click on the below " +
               "url activate your account : "+
               "http://localhost:9999/api/auth/accountVerification/"+token));
    }

    private String generateVerificationToken(User user) {
      String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
    Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
    verificationToken.orElseThrow (()-> new SpringRedditException ("InvalidToken"));
    fetchUserAndEnable(verificationToken.get ());
    }
    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser ().getUsername ();
       User user =  userRepository.findByUsername(username).orElseThrow (()->new SpringRedditException ("User Not Found with name - "+username));
       user.setEnabled (true);
       userRepository.save (user);
    }

    public void login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
        ,loginRequest.getPassword()));
    }
}
