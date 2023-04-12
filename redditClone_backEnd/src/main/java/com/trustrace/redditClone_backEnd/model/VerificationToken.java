package com.trustrace.redditClone_backEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TokenList")
public class VerificationToken {
    @Id
    private String id;
    private String token;
    private User user;
    private Instant expiryDate;
}
