package com.classroom_api.project.service.imp;

import com.classroom_api.project.exception.EmailAlreadyExistsException;
import com.classroom_api.project.mapper.UserMapper;
import com.classroom_api.project.model.auth.AuthenticationRequest;
import com.classroom_api.project.model.auth.AuthenticationResponse;
import com.classroom_api.project.model.user.UserRequestDTO;
import com.classroom_api.project.repository.UserRepository;
import com.classroom_api.project.security.JwtService;
import com.classroom_api.project.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public void register(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("This "+ userRequestDTO.getEmail()+" email already register, Please register for different email address");
        }
        var user = userMapper.toEntity(userRequestDTO);
        user.setPassword(encoder.encode(userRequestDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),authenticationRequest.getPassword()));

        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .responseDTO(userMapper.toDTO(user))
                .build();
    }
}
