package com.trustrace.redditClone_backEnd.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @NotBlank(message = "name is Required")
    private String name;
    @NotBlank(message = "description is Required")
    private String description;
    private List<Post> posts;
    private Instant createDate;
    private User user;
}
