package com.trustrace.prottservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ViewPoint")
public class User {
    @Id
    private String id;
    private String name;
    private String location;
    private Instant createdData;
}
