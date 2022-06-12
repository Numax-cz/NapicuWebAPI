package com.napicu.napicuwebapi.Response;

public class ResponseModel<T> {
    public T data;

    public ResponseModel(T data) {
        this.data = data;
    }
}

