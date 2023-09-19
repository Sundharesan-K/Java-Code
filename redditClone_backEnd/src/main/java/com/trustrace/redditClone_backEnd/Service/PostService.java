package com.trustrace.redditClone_backEnd.Service;

import com.trustrace.redditClone_backEnd.dto.PostRequest;
import com.trustrace.redditClone_backEnd.dto.PostResponse;
import com.trustrace.redditClone_backEnd.exceptions.PostNotFoundException;
import com.trustrace.redditClone_backEnd.exceptions.SpringRedditException;
import com.trustrace.redditClone_backEnd.exceptions.SubredditNotFoundException;
import com.trustrace.redditClone_backEnd.mapper.PostMapper;
import com.trustrace.redditClone_backEnd.model.Post;
import com.trustrace.redditClone_backEnd.model.Subreddit;
import com.trustrace.redditClone_backEnd.model.User;
import com.trustrace.redditClone_backEnd.repository.PostRepository;
import com.trustrace.redditClone_backEnd.repository.SubredditRepository;
import com.trustrace.redditClone_backEnd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(()->new SpringRedditException("Please try again, This subreddit Name not for exists here "+postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest,subreddit,authService.getCurrentUser()));
    }

    public PostResponse getPost(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException("Please try again, This postId not for exists here "+id));
        return postMapper.mapToDto(post);
    }
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
    public List<PostResponse> getPostsBySubreddit(String subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).toList();
    }
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }
}
