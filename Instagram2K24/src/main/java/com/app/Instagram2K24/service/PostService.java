package com.app.Instagram2K24.service;

import com.app.Instagram2K24.dto.PostDto;
import com.app.Instagram2K24.model.Post;

public interface PostService {

    PostDto postCreate(Post post) throws Exception;
}
