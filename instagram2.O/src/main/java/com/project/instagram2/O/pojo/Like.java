package com.project.instagram2.O.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "like")
public class Like {
    private String postId;
    private List<String> likesList;
    public Like(String postId){
        this.postId=postId;
        this.likesList=new ArrayList<>();
    }
}
