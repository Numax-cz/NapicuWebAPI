package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.RateLimit;
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
    public ResponseEntity<NapicuPopJonanekResponseModel>setGetCounter(@RequestBody NapicuPopJonanekPostModel ApiData) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            return popJonanekService.updateAndGetCounter(ApiData.counter);
        }
        throw new RequestException(HttpStatus.TOO_MANY_REQUESTS, null);
    }
}
