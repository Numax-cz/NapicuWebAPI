package com.napicu.napicuwebapi.exception;

import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

public class RequestExceptionSchema {
    @NotNull
    public int status;
    @NotNull
    public int code;


    public RequestExceptionSchema(int status, int code) {
        this.status = status;
        this.code = code;
    }
}
