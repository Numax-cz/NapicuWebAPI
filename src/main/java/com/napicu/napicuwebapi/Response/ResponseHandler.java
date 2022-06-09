package com.napicu.napicuwebapi.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> Response( HttpStatus status, Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        map.put("data", data);
        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> ResponseError(HttpStatus status, int code){
        return new ResponseEntity<Object>(ResponseHandler.GenerateResponseError(status, code), status);
    }

    public static ResponseEntity<Object> ResponseError(HttpStatus status){
        return new ResponseEntity<Object>(ResponseHandler.GenerateResponseError(status, status.value()), status);
    }

    protected static  Map<String, Object> GenerateResponseError(HttpStatus status, int code){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        map.put("code", code);
        return map;
    }
}
