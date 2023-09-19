package com.classroom_api.project.mapper;

import com.classroom_api.project.entity.User;
import com.classroom_api.project.model.user.UserRequestDTO;
import com.classroom_api.project.model.user.UserResponseDTO;

public interface UserMapper {
    User toEntity(UserRequestDTO requestDTO);

    UserResponseDTO toDTO(User user);
}
