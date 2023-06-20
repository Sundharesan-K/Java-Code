package com.trustrace.redditClone_backEnd.model;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "postList")
public class Post {
    @Id
    private String postId;
    @NotBlank
    private String postName;
    private String url;
    private String description;
    private Integer voteCount = 0;
    private User user;
    private Instant createDate;
    private Subreddit subreddit;
}
