package com.project.instagram2.O.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "following")
public class Following {
    private final String userId;
    private long followingCount;
    private List<String> followingList;
    public Following(String userId){
        this.userId=userId;
        this.followingCount=0;
        this.followingList=new ArrayList<>();
    }
}
