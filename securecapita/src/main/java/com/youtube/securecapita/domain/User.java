package com.youtube.securecapita.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Slf4j
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @NotEmpty(message = "First name cannot be Empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be Empty")
    private String lastName;
    @Email(message = "Invalid Email. Please enter a valid Email address")
    private String email;
    @NotEmpty(message = "Password cannot be Empty")
    private String password;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private boolean enabled;
    private boolean isNoteLocked;
    private boolean isUsingMfa;
    private LocalDateTime createAt;
}
