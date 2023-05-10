package com.project.instagram2.O.service;

import com.project.instagram2.O.pojo.Post;
import com.project.instagram2.O.repository.PostRepository;
import com.project.instagram2.O.service.validators.IdNameAndEmailValidator;
import com.project.instagram2.O.service.validators.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private IdNameAndEmailValidator idNameAndEmailValidator;
    @Autowired
    private PostValidator postValidator;
    public void createPost(String userId, String imageUrl)throws Exception {
        idNameAndEmailValidator.isIdExist (userId);
        postRepository.createPost(userId,imageUrl);
    }

    public Post getPostById(String postId) {
        postValidator.isPostExist (postId);
        return postRepository.getPostById (postId);
    }
}
