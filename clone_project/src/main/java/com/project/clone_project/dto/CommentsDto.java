package com.project.clone_project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private String id;
    private String postId;
    private Instant createdDate;
    @NotBlank
    private String text;
    private String username;
}
