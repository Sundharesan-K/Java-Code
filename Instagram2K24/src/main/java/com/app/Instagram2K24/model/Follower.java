package com.app.Instagram2K24.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Following")
public class Follower{
    @Id
    private String id;
    private UserProfile followerName;
    private String username;
    private LocalDateTime create_ts;
}
