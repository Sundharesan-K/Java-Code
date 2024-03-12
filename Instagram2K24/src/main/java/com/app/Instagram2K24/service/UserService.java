package com.app.Instagram2K24.service;

import com.app.Instagram2K24.dto.UserDto;

public interface UserService {
    String userLogin(UserDto userDto) throws Exception;

    String changeStatus(String username) throws Exception;
}
