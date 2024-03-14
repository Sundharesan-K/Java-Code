package com.app.Instagram2K24.service.impl;

import com.app.Instagram2K24.Repo.UserRepository;
import com.app.Instagram2K24.dao.FollowDao;
import com.app.Instagram2K24.dao.UserDao;
import com.app.Instagram2K24.model.Follower;
import com.app.Instagram2K24.model.UserProfile;
import com.app.Instagram2K24.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.app.Instagram2K24.constant.BasicConstant.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowDao followDao;
    private final UserDao userDao;
    private final UserRepository userRepository;
    @Override
    public String followTheUser(String followerName, String username) throws Exception {
        Follower follower = new Follower();
        UserProfile userProfile = userDao.findUserFromUsername(followerName);
        UserProfile followUser = userDao.findUserFromUsername(username);
        try {
            if (Objects.nonNull(userProfile) && Objects.nonNull(followUser)) {
                setFollowerInUser(followerName, username, userProfile, followUser);
                follower(username, follower, userProfile);
                return "Successfully follow the user";
            } else {
                throw new Exception(NOT_FOUND);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private void setFollowerInUser(String followerName, String username, UserProfile userProfile, UserProfile followUser) throws Exception {
        List<UserProfile> userProfiles = new ArrayList<>();
        if (!userProfile.getFollowers().contains(username)) {
            userProfile.setFollowers(Arrays.asList(username));
            followUser.setFollowings(Arrays.asList(followerName));
            userProfile.setFollowersCount(userProfile.getFollowers().size());
            followUser.setFollowingCount(followUser.getFollowings().size());
            userProfiles.add(userProfile);
            userProfiles.add(followUser);
            userRepository.saveAll(userProfiles);
        }else {
            throw new Exception("Already you followed");
        }
    }

    private void follower(String username, Follower follower, UserProfile userProfile) {
        follower.setFollowerName(userProfile);
        follower.setUsername(username);
        follower.setCreate_ts(LocalDateTime.now());
        followDao.addFollow(follower);
    }
}
