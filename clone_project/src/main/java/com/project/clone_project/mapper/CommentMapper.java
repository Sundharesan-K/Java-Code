package com.project.clone_project.mapper;

import com.project.clone_project.dto.CommentsDto;
import com.project.clone_project.model.Comment;
import com.project.clone_project.model.Post;
import com.project.clone_project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "post",source = "post")
    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "text",source = "commentsDto.text")
    @Mapping(target = "user",source = "user")
    @Mapping(target = "id",ignore = true)
    Comment mapToClass(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    CommentsDto mapToDto(Comment comment);
}
