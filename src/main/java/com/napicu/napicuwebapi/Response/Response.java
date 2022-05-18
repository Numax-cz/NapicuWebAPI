package com.napicu.napicuwebapi.Response;

public class Response{
    public boolean success;
    public Object data;

    public Response(boolean success, Object data){
        this.success = success;
        this.data = data;
    }

}
