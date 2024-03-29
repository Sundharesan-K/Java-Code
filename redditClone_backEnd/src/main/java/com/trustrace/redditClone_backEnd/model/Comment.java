package com.trustrace.redditClone_backEnd.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "CommentList")
public class Comment {
    @Id
    private String id;
    private String text;
    private Post post;
    private Instant createDate;
    private User user;
}
