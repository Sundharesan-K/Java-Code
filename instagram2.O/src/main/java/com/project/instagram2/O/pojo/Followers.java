package com.project.instagram2.O.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "followers")
public class Followers {
    private final String userId;
    private long followersCount;
    private List<String> followersList;
    public Followers(String userId){
        this.userId=userId;
        this.followersCount=0;
        this.followersList=new ArrayList<>();
    }
}
