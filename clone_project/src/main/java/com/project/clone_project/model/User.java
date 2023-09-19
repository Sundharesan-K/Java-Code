package com.project.clone_project.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String userId;

    @NotBlank(message = "Username id required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 5,max = 20,message = "password size is 5 to 20")
    private String password;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    private Instant createdDate;

    private boolean enabled;
}
