package com.classroom_api.project.model.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @Email(message = "Please enter the valid email address")
    @NotNull(message = "Can't be null, Please enter your email address")
    private String email;

    @NotNull(message = "Can't be null, Please enter the password")
    @Size(min = 8,max = 100,
            message = "password size should be between 8 and 32 digit or characters")
    private String password;
}
