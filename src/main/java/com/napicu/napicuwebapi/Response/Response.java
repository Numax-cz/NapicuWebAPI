package com.napicu.napicuwebapi.Response;

public class Response {
    public int status;
    public Object data;

    public Response(int status, Object data) {
        this.status = status;
        this.data = data;
    }

}
