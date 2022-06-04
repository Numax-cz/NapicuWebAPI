package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Response setGetCounter(@RequestBody NapicuPopJonanekModel data) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            return popJonanekService.updateAndGetCounter(data.counter);
        }
        return new Response(HttpStatus.TOO_MANY_REQUESTS.value(), null);
    }
}
