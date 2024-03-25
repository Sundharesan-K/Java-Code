package com.app.Instagram2K24;

import com.app.Instagram2K24.dto.PostDto;
import com.app.Instagram2K24.model.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperClass {

    PostDto mapToDto(Post post);
}
