package com.project.clone_project.service;

import com.project.clone_project.dto.PostRequest;
import com.project.clone_project.dto.PostResponse;
import com.project.clone_project.exception.PostNotFountException;
import com.project.clone_project.exception.SpringRedditException;
import com.project.clone_project.exception.SubredditNotFoundException;
import com.project.clone_project.mapper.PostMapper;
import com.project.clone_project.model.Post;
import com.project.clone_project.model.Subreddit;
import com.project.clone_project.model.User;
import com.project.clone_project.repository.PostRepo;
import com.project.clone_project.repository.SubredditRepo;
import com.project.clone_project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepo postRepo;
    private final PostMapper postMapper;
    private final SubredditRepo subredditRepo;
    private final AuthService authService;
    private final UserRepo userRepo;

    public void createPost(PostRequest postRequest) {
        Subreddit subreddit = subredditRepo.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SpringRedditException(postRequest.getSubredditName() + " subredditName does not here"));
        postRepo.save(postMapper.mapDtoToPost(postRequest,subreddit,authService.getCurrentUser()));
    }

    public List<PostResponse> getAllPosts() {
        return postRepo.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }

    public PostResponse getById(String postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new PostNotFountException("This "+postId+" postId not Found"));
        return postMapper.mapToDto(post);
    }

    public List<PostResponse> getPostBySubreddit(String id) {
        Subreddit subreddit = subredditRepo.findById(id)
                .orElseThrow(()-> new SubredditNotFoundException("Not Found"));
        List<Post> posts = postRepo.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).toList();
    }

    public List<PostResponse> getByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new PostNotFountException(username+" name not found"));
        return postRepo.findPostByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }
}
