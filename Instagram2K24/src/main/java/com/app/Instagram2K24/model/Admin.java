package com.app.Instagram2K24.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document(collection = "admin")
public class Admin {
    @Id
    private String id;
    @Field(value = "email_id")
    private String emailId;
    private String password;
    private String role;
}
