package com.app.Instagram2K24.model;

import com.app.Instagram2K24.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_profile")
public class UserProfile {
    @Id
    private String id;
    private String username;
    private String fullName;
    private String emailId;
    private String password;
    private List<Post> posts;
    private List<Followers> followers;
    private List<Following> followings;
    private Integer followersCount = 0;
    private Integer followingCount = 0;
    private Status status;
}
