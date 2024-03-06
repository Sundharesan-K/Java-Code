package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "adminInfo")
public class Admin {
    @Field("username")
   private String username;
    @Field("password")
   private String password;

}
