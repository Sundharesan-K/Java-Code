package com.classroom_api.project.service;

import com.classroom_api.project.model.user.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO getUser(String id);

    List<UserResponseDTO> getUsersByName(String userName);

    UserResponseDTO findUserByEmail(String email);
}
