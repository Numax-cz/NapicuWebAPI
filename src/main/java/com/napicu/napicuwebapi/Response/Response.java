package com.napicu.napicuwebapi.Response;

import org.springframework.http.HttpStatus;

public class Response {
    public int status;
    public Object data;

    public Response(HttpStatus status, Object data) {
        this.status = status.value();
        this.data = data;
    }

}
