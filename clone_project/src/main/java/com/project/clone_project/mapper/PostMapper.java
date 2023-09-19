package com.project.clone_project.mapper;

import com.project.clone_project.dto.PostRequest;
import com.project.clone_project.dto.PostResponse;
import com.project.clone_project.model.*;
import com.project.clone_project.repository.CommentRepo;
import com.project.clone_project.repository.VoteRepo;
import com.project.clone_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    private  CommentRepo commentRepo;
    @Autowired
    private AuthService authService;
    @Autowired
    private VoteRepo voteRepo;

    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "description",source = "postRequest.description")
    @Mapping(target = "subreddit",source = "subreddit")
    @Mapping(target = "user",source = "user")
    @Mapping(target = "voteCount",constant = "0")
    public abstract Post mapDtoToPost(PostRequest postRequest,Subreddit subreddit,User user);

    @Mapping(target = "id",source = "postId")
    @Mapping(target = "subredditName",source = "subreddit.name")
    @Mapping(target = "username",source = "user.username")
    @Mapping(target = "commentCount",expression = "java(commentCount(post))")
    @Mapping(target = "upVote",expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote",expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post){
        return commentRepo.findByPost(post).size();
    }

    boolean isPostUpVoted(Post post){
        return checkVoteType(post, VoteType.UPVOTE);
    }

    boolean isPostDownVoted(Post post){
        return checkVoteType(post,VoteType.DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()){
            Optional<Vote> optionalVote = voteRepo.findTopByPostAndUserOrderByVoteIdDesc(post,
                    authService.getCurrentUser());
            return optionalVote.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }

}

