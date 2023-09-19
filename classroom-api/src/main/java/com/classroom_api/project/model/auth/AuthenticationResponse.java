package com.classroom_api.project.model.auth;

import com.classroom_api.project.model.user.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuthenticationResponse {
    private String token;
    private UserResponseDTO responseDTO;
}
