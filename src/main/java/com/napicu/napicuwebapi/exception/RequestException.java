package com.napicu.napicuwebapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RequestException extends RuntimeException {
    public HttpStatus status;
    public NapicuExceptions code;

    public RequestException(HttpStatus status, NapicuExceptions code) {
        this.status = status;
        this.code = code;
    }
}
