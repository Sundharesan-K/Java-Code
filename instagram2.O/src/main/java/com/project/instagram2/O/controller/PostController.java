package com.project.instagram2.O.controller;

import com.project.instagram2.O.pojo.Post;
import com.project.instagram2.O.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PutMapping("{userId}/create/{imageUrl}")
    public String createPost(@PathVariable String userId, @PathVariable String imageUrl) throws Exception {
        postService.createPost(userId, imageUrl);
        return "Post created";
    }

    @GetMapping("/id/{postId}")
    public Post getPostById(@PathVariable String postId)throws Exception{
        return postService.getPostById(postId);
    }

}
