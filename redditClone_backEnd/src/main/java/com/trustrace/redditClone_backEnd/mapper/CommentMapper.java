package com.trustrace.redditClone_backEnd.mapper;

import com.trustrace.redditClone_backEnd.dto.CommentDto;
import com.trustrace.redditClone_backEnd.model.Comment;
import com.trustrace.redditClone_backEnd.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "text",source = "commentDto.text")
    @Mapping(target = "createDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "post",source = "post")
    Comment map(CommentDto commentDto, Post post);

    @Mapping(target = "postId",expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "username",expression = "java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);
}
