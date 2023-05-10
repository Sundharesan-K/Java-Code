package com.project.instagram2.O.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIResponse {
    private Integer status;
    private Object data;
    private Object error;

    public APIResponse() {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.error = error;
    }
}
