package com.project.instagram2.O.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class SignUp {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String userId;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String roles;
}
