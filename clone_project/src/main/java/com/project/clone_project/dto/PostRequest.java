package com.project.clone_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PostRequest {
    private String postId;
    private String subredditName;
    private String postName;
    private String url;
    private String description;
}
