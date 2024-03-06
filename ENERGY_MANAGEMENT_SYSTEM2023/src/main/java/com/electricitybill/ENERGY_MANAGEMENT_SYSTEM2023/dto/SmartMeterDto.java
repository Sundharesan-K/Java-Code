package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class SmartMeterDto {
    @Id
    private String id;
    private String meterId;
    private String username;
    private String provider;
    private String status;
    private List<ReadingDto> readings;
}

