package com.project.clone_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SubredditDto {
    private String id;
    private String name;
    private String description;
    private Integer numberOfPosts;


}
