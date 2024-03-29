package com.trustrace.security30.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document("person")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String roles;


}
