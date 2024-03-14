package com.app.Instagram2K24.dao;

import com.app.Instagram2K24.model.UserProfile;

import java.util.List;

public interface UserDao {
    UserProfile findUserFromEmailId(String emailId);

    void addUser(UserProfile userProfiler);

    UserProfile findUserFromUsername(String username);

    void UsersAdd(List<UserProfile> list);
}
