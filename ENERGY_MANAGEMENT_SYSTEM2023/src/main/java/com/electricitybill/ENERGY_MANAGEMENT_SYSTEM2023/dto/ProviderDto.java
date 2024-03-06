package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProviderDto {
    @Id
    private String id;

    private String name;

    private Integer rate;

    private Boolean active;

}