package com.project.clone_project.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "refreshToken")
public class RefreshToken {
    @Id
    private String id;
    private String token;
    private Instant createdDate;
}
