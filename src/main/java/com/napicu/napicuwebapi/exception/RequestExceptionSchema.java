package com.napicu.napicuwebapi.exception;

import org.springframework.http.HttpStatus;
public class RequestExceptionSchema {
    public int status;
    public int code;


    public RequestExceptionSchema(int status, int code) {
        this.status = status;
        this.code = code;
    }
}
