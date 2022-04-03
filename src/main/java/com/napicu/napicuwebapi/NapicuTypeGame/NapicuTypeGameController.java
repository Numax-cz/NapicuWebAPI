package com.napicu.napicuwebapi.NapicuTypeGame;

import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekModel;
import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekService;
import com.napicu.napicuwebapi.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class NapicuTypeGameController {
    @Autowired
    private NapicuTypeGameService typeGameService;


    @PostMapping("/words")
    public Response setGetCounter(@RequestBody NapicuTypeGameModel data){
        return new Response(true,  typeGameService.getWords(data.wordCount));
    }
}
