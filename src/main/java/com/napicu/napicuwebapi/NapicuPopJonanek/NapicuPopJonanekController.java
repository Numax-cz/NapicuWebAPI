package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.Database.NapicuPopJonanekDatabase;
import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.RateLimit;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;


@RestController
public class NapicuPopJonanekController {
    @Autowired
    private NapicuPopJonanekService popJonanekService;

    @Autowired
    private RateLimit rateLimit;


    public NapicuPopJonanekController(){

    }

    @PostMapping("/popjonanek")
    public Response setGetCounter(@RequestBody NapicuPopJonanekModel data){
        return new Response(true,  popJonanekService.updateAndGetCounter(data.counter));
    }

    @GetMapping("/test")
    public ResponseEntity<String> get(){
        if(rateLimit.getServiceBucket().tryConsume(1)){
            return new ResponseEntity<String>("Goot", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Not goot", HttpStatus.OK);
    }
}
