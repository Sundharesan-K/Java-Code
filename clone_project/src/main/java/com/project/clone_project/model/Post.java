package com.project.clone_project.model;

import com.mongodb.lang.Nullable;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
@Document(collection = "posts")
public class Post {
    @Id
    private String postId;

    @NotBlank(message = "Post Name cannot be empty or null")
    @Size(min = 4,max = 20,message = "postName minimum 4 to maximum 20 characters")
    private String postName;

    @Nullable
    private String url;

    @Nullable
    @Lob
    private String description;

    private Integer voteCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subreddit subreddit;
}
