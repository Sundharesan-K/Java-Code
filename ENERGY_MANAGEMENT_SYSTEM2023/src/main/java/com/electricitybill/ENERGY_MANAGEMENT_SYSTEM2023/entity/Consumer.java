package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "consumer")
public class Consumer {
    @Id
    private String id;
    @Field("firstName")
    private String firstName;
    @Field("lastName")
    private String lastName;
    @Field("username")
    private String username;
    @Field("meter_id")
    private List<String> meterId;
}
