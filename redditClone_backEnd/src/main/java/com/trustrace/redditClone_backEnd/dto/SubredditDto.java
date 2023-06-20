package com.trustrace.redditClone_backEnd.dto;

import com.trustrace.redditClone_backEnd.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {
    private String id;
    private String name;
    private String description;
    private Integer numberOfPosts ;
}
