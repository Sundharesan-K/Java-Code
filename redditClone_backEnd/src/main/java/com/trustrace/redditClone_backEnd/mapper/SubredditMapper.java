package com.trustrace.redditClone_backEnd.mapper;

import com.trustrace.redditClone_backEnd.dto.SubredditDto;
import com.trustrace.redditClone_backEnd.model.Post;
import com.trustrace.redditClone_backEnd.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;


@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")

    SubredditDto  mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        if (Objects.isNull(numberOfPosts)){
            return 0;
        }else {
            return numberOfPosts.size();
        }
    }
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "createDate",expression = "java(java.time.Instant.now())")
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}
