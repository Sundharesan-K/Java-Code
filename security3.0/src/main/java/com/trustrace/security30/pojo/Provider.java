package com.trustrace.security30.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "providers")
public class Provider {
    @Id
    private String id;
    private String name;
    private Double amountPerKwh;
    private String status;
}
