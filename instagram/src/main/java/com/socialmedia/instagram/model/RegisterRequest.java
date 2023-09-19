package com.socialmedia.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
