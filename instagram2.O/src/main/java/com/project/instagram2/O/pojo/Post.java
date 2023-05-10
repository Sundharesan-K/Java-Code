package com.project.instagram2.O.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@Document(collection = "post")
public class Post {
    private final String userId;
    @Id
    private String postId;
    private String imageUrl;
    private long likeCount;
    private Date uploadedDateAndTime;

    public Post(String userId,String imageUrl){
        this.userId=userId;
        this.imageUrl=imageUrl;
        this.likeCount=0;
        this.uploadedDateAndTime=new Date();
    }
}
