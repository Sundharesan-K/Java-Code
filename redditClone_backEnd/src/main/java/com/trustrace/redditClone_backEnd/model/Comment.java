package com.trustrace.redditClone_backEnd.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "CommentList")
public class Comment {
    @Id
    private Long id;
    @NotEmpty
    private String text;
    private Post post;
    private Instant createDate;
    private User user;
}
