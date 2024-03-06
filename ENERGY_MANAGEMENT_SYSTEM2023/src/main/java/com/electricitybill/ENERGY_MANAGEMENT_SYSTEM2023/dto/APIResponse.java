package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {
    private String message;
    private Object data;
}
