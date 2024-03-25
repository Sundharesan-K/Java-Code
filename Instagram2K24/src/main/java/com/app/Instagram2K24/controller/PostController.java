package com.app.Instagram2K24.controller;

import com.app.Instagram2K24.JWT.JwtService;
import com.app.Instagram2K24.dto.APIResponse;
import com.app.Instagram2K24.dto.PostDto;
import com.app.Instagram2K24.model.Post;
import com.app.Instagram2K24.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JwtService jwtService;

    @PostMapping("/post-create")
    public ResponseEntity<APIResponse> postCreate(@RequestHeader("authorization") String auth,
        @RequestBody Post post) {
        APIResponse response = new APIResponse();
        if (jwtService.validateUserToken(auth)){
            try {
                PostDto postDto = postService.postCreate(post);
                response.setMessage("Posted Created Successfully");
                response.setData(postDto);
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
