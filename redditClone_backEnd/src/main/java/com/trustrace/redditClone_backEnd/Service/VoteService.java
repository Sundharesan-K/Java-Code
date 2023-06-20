package com.trustrace.redditClone_backEnd.Service;

import com.trustrace.redditClone_backEnd.dto.VoteDto;
import com.trustrace.redditClone_backEnd.exceptions.PostNotFoundException;
import com.trustrace.redditClone_backEnd.exceptions.SpringRedditException;
import com.trustrace.redditClone_backEnd.model.Post;
import com.trustrace.redditClone_backEnd.model.Vote;
import com.trustrace.redditClone_backEnd.repository.PostRepository;
import com.trustrace.redditClone_backEnd.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.trustrace.redditClone_backEnd.model.VoteType.UPVOTE;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteService {
    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final AuthService authService;

    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(()->new PostNotFoundException("Post Not Found with ID -" +voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())){
            throw new SpringRedditException("You Already "+voteDto.getPostId()+" id for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())){
            post.setVoteCount(post.getVoteCount() + 1);
        }else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto,post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto,Post post){
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
