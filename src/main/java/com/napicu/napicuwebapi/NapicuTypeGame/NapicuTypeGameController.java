package com.napicu.napicuwebapi.NapicuTypeGame;

import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekModel;
import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekService;
import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
public class NapicuTypeGameController {
    @Autowired
    private NapicuTypeGameService typeGameService;
    @Autowired
    private RateLimit rateLimit;


    @GetMapping("/words")
    @ResponseBody
    public Response setGetCounter(@RequestParam String count){
        if(rateLimit.getServiceBucket().tryConsume(1)){
            return typeGameService.getWords(Integer.parseInt(count));
        }
        return new Response(HttpStatus.TOO_MANY_REQUESTS.value(), null);
    }
}
