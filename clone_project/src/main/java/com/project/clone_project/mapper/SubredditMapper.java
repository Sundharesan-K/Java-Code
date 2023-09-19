package com.project.clone_project.mapper;

import com.project.clone_project.dto.SubredditDto;
import com.project.clone_project.model.Post;
import com.project.clone_project.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
    @InheritInverseConfiguration
    @Mapping(target = "posts",ignore = true)
    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);

    default Integer mapPosts(List<Post> numberOfPosts){
        if (numberOfPosts == null){
            return 0;
        }
        return numberOfPosts.size();
    }
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);
}
