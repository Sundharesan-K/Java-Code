package com.app.Instagram2K24.service.impl;

import static com.app.Instagram2K24.constant.BasicConstant.NOT_FOUND;

import com.app.Instagram2K24.MapperClass;
import com.app.Instagram2K24.constant.BasicConstant;
import com.app.Instagram2K24.dao.PostDao;
import com.app.Instagram2K24.dao.UserDao;
import com.app.Instagram2K24.dto.CommentDto;
import com.app.Instagram2K24.dto.PostDto;
import com.app.Instagram2K24.model.Post;
import com.app.Instagram2K24.model.UserProfile;
import com.app.Instagram2K24.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDao postDao;
    private final UserDao userDao;
    private final MapperClass mapper;

    @Override
    public PostDto postCreate(Post post) throws Exception {
        Post post1 = new Post();
        PostDto postDto = new PostDto();
        UserProfile userProfile = userDao.findById(post.getUserId());
        try {
            if (Objects.nonNull(userProfile)){
                postSaveProcess(post, post1);
                postDao.addPost(post1);
                postDto = mapper.mapToDto(post1);
                userProfile.setPosts(Arrays.asList(postDto));
                userDao.addUser(userProfile);
            }else {
                throw new Exception(NOT_FOUND);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return postDto;
    }

    private void postSaveProcess(Post post, Post post1) {
        List<CommentDto> commentDtoList = Collections.EMPTY_LIST;
        List<String> list = Collections.EMPTY_LIST;
        post1.setUserId(post.getUserId());
        post1.setPostName(post.getPostName());
        post1.setComments(commentDtoList);
        post1.setCreate_ts(LocalDateTime.now());
        post1.setUpdate_ts(LocalDateTime.now());
        post1.setLikesUsername(list);
    }
}
