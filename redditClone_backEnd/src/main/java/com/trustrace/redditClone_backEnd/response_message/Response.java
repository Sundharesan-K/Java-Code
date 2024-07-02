package com.trustrace.redditClone_backEnd.response_message;

import lombok.Getter;

@Getter
public enum Response {
    SUCCESS("success"),

    SUCCESS_MESSAGE("success_message"),

    ERROR_CODE("400"),

    ERROR_MESSAGE("failure_message"),

    FAILURE("failure");

    private final String message;

    Response(String message) {
        this.message = message;
    }
}
