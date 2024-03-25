package com.app.Instagram2K24.dto;

import com.app.Instagram2K24.model.Comment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String postName;
    private List<String> likesUsername;
    private Integer likeCounts = 0;
    private List<CommentDto> comments;
}
