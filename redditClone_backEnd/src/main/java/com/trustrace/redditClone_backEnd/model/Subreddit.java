package com.trustrace.redditClone_backEnd.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "subredditList")
public class Subreddit {
    @Id
    private String id;
    private String name;
    private String description;
    private List<Post> posts;
    private Instant createDate;
    private User user;
}
