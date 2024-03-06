package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class ConsumerDto {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private List<String> meterId;
}