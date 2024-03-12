package com.app.Instagram2K24.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private String id;
    private String postId;
    private String postName;
    private String message;
}
