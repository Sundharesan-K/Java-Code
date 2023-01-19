package com.socialmedia.instagram.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Post {
    private long postId;
    private String imageUrl;
    private long likeCount;
    public Post(long postId, String imageUrl) {
        this.postId = postId;
        this.imageUrl = imageUrl;
        this.likeCount = 0;
    }
    public long getPostId() { return postId; }
    public void setPostId(long postId) { this.postId = postId; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public long getLikeCount() { return likeCount; }
    public void setLikeCount(long likeCount) { this.likeCount = likeCount; }
}
