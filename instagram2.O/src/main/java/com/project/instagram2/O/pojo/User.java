package com.project.instagram2.O.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "user")
public class User {
    private String userId;
    private String userName;
    private String bio;
    private Binary profilePicture;
    private long followersCount;
    private long followingCount;
    private long numberOfPosts;

    public User(String userId,String userName){
        this.userId=userId;
        this.userName=userName;
        this.bio="";
        this.followersCount=0;
        this.followingCount=0;
        this.numberOfPosts=0;
    }
}
