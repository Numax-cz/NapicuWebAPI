package com.napicu.napicuwebapi.NapicuTypeGame;

import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NapicuTypeGameController {
    @Autowired
    private NapicuTypeGameService typeGameService;
    @Autowired
    private RateLimit rateLimit;


    @GetMapping("/words")
    @ResponseBody
    public Response setGetCounter(@RequestParam String count) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            return typeGameService.getWords(Integer.parseInt(count));
        }
        return new Response(HttpStatus.TOO_MANY_REQUESTS.value(), null);
    }
}
