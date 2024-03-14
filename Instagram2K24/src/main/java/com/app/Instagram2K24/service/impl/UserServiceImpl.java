package com.app.Instagram2K24.service.impl;

import com.app.Instagram2K24.model.Post;
import com.app.Instagram2K24.validation.DataUtil;
import com.app.Instagram2K24.dao.UserDao;
import com.app.Instagram2K24.dto.UserDto;
import com.app.Instagram2K24.model.UserProfile;
import com.app.Instagram2K24.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.app.Instagram2K24.constant.BasicConstant.*;
import static com.app.Instagram2K24.dto.Status.Private;
import static com.app.Instagram2K24.dto.Status.Public;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    @Override
    public String userLogin(UserDto userDto) throws Exception {
        try {
            if (DataUtil.isValidEmailString(userDto.getEmailId())) {
                UserProfile user = userDao.findUserFromEmailId(userDto.getEmailId());
                if (Objects.isNull(user)) {
                    userCreated(userDto);
                    return USER_CREATED_SUCCESSFULLY;
                } else {
                    throw new Exception(USER_ALREADY_EXISTS);
                }
            } else {
                throw new Exception(VALID_EMAIL);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

        private void userCreated (UserDto userDto){
            List<Post> postList = Collections.EMPTY_LIST;
            List<String> list = Collections.EMPTY_LIST;
            List<String> list1 = Collections.EMPTY_LIST;
            UserProfile userProfiler = new UserProfile();
            userProfiler.setUsername(userDto.getUsername());
            userProfiler.setFullName(userDto.getFullName());
            userProfiler.setEmailId(userDto.getEmailId());
            userProfiler.setPassword(encoder.encode(userDto.getPassword()));
            userProfiler.setPosts(postList);
            userProfiler.setFollowers(list);
            userProfiler.setFollowings(list1);
            userProfiler.setStatus(Public);
            userProfiler.setCreate_ts(LocalDateTime.now());
            userDao.addUser(userProfiler);
        }

    @Override
    public String changeStatus(String username) throws Exception {
        UserProfile userFromUsername = userDao.findUserFromUsername(username);
        try {
            if (Objects.nonNull(userFromUsername)) {
                if (userFromUsername.getStatus() != Private) {
                    userFromUsername.setStatus(Private);
                    userDao.addUser(userFromUsername);
                    return "Successfully Status changed in Private";
                } else {
                    throw new Exception("Already you have private");
                }
            } else {
                throw new Exception(NOT_FOUND);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
