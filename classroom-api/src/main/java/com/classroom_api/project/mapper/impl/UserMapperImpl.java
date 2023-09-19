package com.classroom_api.project.mapper.impl;

import com.classroom_api.project.entity.User;
import com.classroom_api.project.mapper.UserMapper;
import com.classroom_api.project.model.Role;
import com.classroom_api.project.model.user.UserRequestDTO;
import com.classroom_api.project.model.user.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserRequestDTO requestDTO) {
        User newUser = new User();
        newUser.setUserName(requestDTO.getUserName());
        newUser.setEmail(requestDTO.getEmail());
        newUser.setPassword(requestDTO.getPassword());
        newUser.setRole(Role.USER);
        return newUser;
    }

    @Override
    public UserResponseDTO toDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUserName(user.getActualName());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }
}
