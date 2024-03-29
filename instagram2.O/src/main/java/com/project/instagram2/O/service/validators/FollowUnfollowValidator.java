package com.project.instagram2.O.service.validators;

import com.project.instagram2.O.repository.FollowUnfollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowUnfollowValidator {
    @Autowired
    IdNameAndEmailValidator idAndNameValidator;
    @Autowired
    FollowUnfollowRepository followUnfollowRepository;
    public void preValidate(String userId, String userIdToFollowOrUnfollow, boolean isFollow) throws Exception {
        idAndNameValidator.isIdExist(userId);
        idAndNameValidator.isIdExist(userIdToFollowOrUnfollow);

        if(isBothIdEqual(userId, userIdToFollowOrUnfollow)) {
            throw new Exception("Both id are same");
        }

        if(isFollow) { // follow user
            if(isAlreadyFollowed(userId, userIdToFollowOrUnfollow)) {
                throw new Exception(userId + " is already following " + userIdToFollowOrUnfollow + " cannot follow again");
            }
        } else { // unfollow user
            if(! isAlreadyFollowing(userId, userIdToFollowOrUnfollow)) {  // if the user is in the followers list it return true
                throw new Exception(userId + " is not following " + userIdToFollowOrUnfollow);
            }
        }
    }
    private boolean isBothIdEqual(String userId, String userIdToFollow) {
        return userId.equals(userIdToFollow);
    }
    private boolean isAlreadyFollowed(String userId, String userIdToFollowOrUnfollow) {
        return followUnfollowRepository.getFollowingList(userId)
                .contains(userIdToFollowOrUnfollow);
    }
    private boolean isAlreadyFollowing(String userId, String userIdToFollowOrUnfollow) {
        return followUnfollowRepository.getFollowersList(userId)
                .contains(userIdToFollowOrUnfollow);
    }
}
