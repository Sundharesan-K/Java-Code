package com.classroom_api.project.service;

import com.classroom_api.project.model.auth.AuthenticationRequest;
import com.classroom_api.project.model.auth.AuthenticationResponse;
import com.classroom_api.project.model.user.UserRequestDTO;

public interface AuthenticationService {

    void register(UserRequestDTO userRequestDTO);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
