package com.trustrace.backendspring.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {

    private Object data;

    private String message;

    private String status;

    private String errorCode;
}
