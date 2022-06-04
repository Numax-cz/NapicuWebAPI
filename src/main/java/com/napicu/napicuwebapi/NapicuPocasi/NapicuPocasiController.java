package com.napicu.napicuwebapi.NapicuPocasi;

import com.fasterxml.jackson.databind.JsonNode;
import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekModel;
import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.NapicuPrint;
import com.napicu.napicuwebapi.service.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;

@RestController
public class NapicuPocasiController {

    @Autowired
    private RateLimit rateLimit;
    @Value("${api.keys.open-weather}")
    private String api_key;
    @Autowired
    private NapicuPocasiService pocasiService;

    @PostConstruct
    public void init() {
        if(Objects.equals(this.api_key, "")){
            new NapicuPrint().printError("OpenWeather", "Open weather key is not set");
            Runtime.getRuntime().halt(0);
        }
    }

    @GetMapping("/pocasi")
    public Response get(@RequestBody NapicuPocasiModel data){
        if(rateLimit.getServiceBucket().tryConsume(1)){

            return this.pocasiService.getOpenWeatherData(this.api_key, data.country);
        }
        return new Response(HttpStatus.TOO_MANY_REQUESTS.value(), null);
    }

}
