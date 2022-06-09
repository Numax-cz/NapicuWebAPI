package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.Response.ResponseHandler;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.RateLimit;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NapicuPopJonanekController {
    @Autowired
    private NapicuPopJonanekService popJonanekService;

    @Autowired
    private RateLimit rateLimit;


    public NapicuPopJonanekController() {

    }

    @PostMapping("/popjonanek")
    public ResponseEntity<Object> setGetCounter(@RequestBody NapicuPopJonanekModel ApiData) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            try{
                Integer data = popJonanekService.updateAndGetCounter(ApiData.counter);
                return ResponseHandler.Response(HttpStatus.OK, data);
            }catch (RequestException error){
                return ResponseHandler.ResponseError(error.status, error.code.value());
            }
        }
        return ResponseHandler.Response(HttpStatus.TOO_MANY_REQUESTS, null);
    }

}
