package com.napicu.napicuwebapi.NapicuIP;


import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NapicuIPController {
    @Autowired
    private NapicuIPService ipService;

    @Autowired
    private RateLimit rateLimit;

    @GetMapping("/ip")
    public Response get() {

        if (rateLimit.getServiceBucket().tryConsume(1)) {
            return ipService.getIpInfo();
        }
        return new Response(HttpStatus.TOO_MANY_REQUESTS, null);

    }

}
