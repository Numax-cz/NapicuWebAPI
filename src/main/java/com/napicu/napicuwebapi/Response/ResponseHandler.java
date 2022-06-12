package com.napicu.napicuwebapi.Response;

import com.napicu.napicuwebapi.exception.RequestExceptionSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler<T> {
    public ResponseEntity<ResponseModel<T>> Response(HttpStatus status, T data) {
        return new ResponseEntity<ResponseModel<T>>(new ResponseModel<T>(data), status);
    }

    public ResponseEntity<RequestExceptionSchema> ResponseError(HttpStatus status, int code){
        return new ResponseEntity<RequestExceptionSchema>(this.GenerateResponseError(status, code), status);
    }

    public ResponseEntity<RequestExceptionSchema> ResponseError(HttpStatus status){
        return new ResponseEntity<RequestExceptionSchema>(this.GenerateResponseError(status, status.value()), status);
    }

    protected RequestExceptionSchema GenerateResponseError(HttpStatus status, int code){
        return new RequestExceptionSchema(status.value(), code);
    }
}