package com.trustrace.security30.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "smartMeters")
public class SmartMeter {
    @Id
    private String id;
    private String meterId;
    private String providerId;
    private String status;
    private List<Readings> readings;

}
