package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "provider")
public class Provider {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("rate")
    private Integer rate;
    @Field("active")
    private Boolean active;
}
