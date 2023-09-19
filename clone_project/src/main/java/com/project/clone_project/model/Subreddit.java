package com.project.clone_project.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "subreddits")
public class Subreddit {
    @Id
    private String id;

    @NotBlank(message = "Subreddit name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    private List<Post> posts;

    private Instant createdDate;

    private User user;
}
