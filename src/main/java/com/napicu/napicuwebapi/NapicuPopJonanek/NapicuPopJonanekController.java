package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.Database.NapicuPopJonanekDatabase;
import com.napicu.napicuwebapi.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NapicuPopJonanekController {
    @Autowired
    private NapicuPopJonanekService popJonanekService;


    @PostMapping("/popjonanek")
    public Response setGetCounter(@RequestBody NapicuPopJonanekModel data){

        return new Response(true,  popJonanekService.updateAndGetCounter(data.counter));
    }


}
