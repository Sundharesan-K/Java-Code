package com.app.Instagram2K24.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "followers")
public class Followers {
    @Id
    private String id;
    private String username;
}
