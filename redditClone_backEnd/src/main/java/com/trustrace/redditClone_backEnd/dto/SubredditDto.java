package com.trustrace.redditClone_backEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer numberOfPost;
}
