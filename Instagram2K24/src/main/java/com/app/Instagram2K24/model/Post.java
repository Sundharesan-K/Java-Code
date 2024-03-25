package com.app.Instagram2K24.model;

import com.app.Instagram2K24.dto.CommentDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "post")
public class Post {
    @Id
    private String id;
    private String userId;
    private String postName;
    private List<String> likesUsername;
    private Integer likeCounts = 0;
    private List<CommentDto> comments;
    private Integer commentCounts = 0;
    private LocalDateTime create_ts;
    private LocalDateTime update_ts;
}
