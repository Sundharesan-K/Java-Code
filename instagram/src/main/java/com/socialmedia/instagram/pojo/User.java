package com.socialmedia.instagram.pojo;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "instagram")
public class User {
    private String userId;
    private String userName;
    private long followersCount;
    private long followingCount;
    private List<Post> posts;
    public User() {
        this.followersCount = 0;
        this.followingCount = 0;
        this.posts = new ArrayList<>();
    }
    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }
    public long getFollowersCount() { return followersCount; }
    public void setFollowersCount(long followersCount) { this.followersCount = followersCount; }
    public long getFollowingCount() { return followingCount; }
    public void setFollowingCount(long followingCount) { this.followingCount = followingCount; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public List<Post> getPosts() { return posts; }
    public void setPost(Post post) {
        System.out.println(post);
        this.posts.add(post);
    }
}
