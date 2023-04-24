package com.trustrace.redditClone_backEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private String postId;
    private String subredditName;
    private String postName;
    private String url;
    private String description;
}
