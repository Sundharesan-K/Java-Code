package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ReadingDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "smart_meter")
public class SmartMeter {
    @Id
    private String id;
    @Field("meter_id")
    private String meterId;
    @Field("username")
    private String username;
    @Field("provider")
    private String provider;
    @Field("status")
    private String status;
    @Field("readings")
    private List<ReadingDto> readings;
}
