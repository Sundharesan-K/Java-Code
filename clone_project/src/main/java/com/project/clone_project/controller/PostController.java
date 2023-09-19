package com.project.clone_project.controller;

import com.project.clone_project.dto.PostRequest;
import com.project.clone_project.dto.PostResponse;
import com.project.clone_project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest){
        postService.createPost(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<List<PostResponse>> getAllPost(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("postId") String postId){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getById(postId));
    }

    @GetMapping("/getSubredditId")
    public ResponseEntity<List<PostResponse>> getPostBYSubreddit(@RequestParam String id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostBySubreddit(id));
    }

    @GetMapping("/getPostByUsername")
    public ResponseEntity<List<PostResponse>> getPostByUsername(@RequestParam String username){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getByUsername(username));
    }
}
