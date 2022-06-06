package com.napicu.napicuwebapi.NapicuPocasi;

import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.NapicuPrint;
import com.napicu.napicuwebapi.service.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
        if (Objects.equals(this.api_key, "")) {
            new NapicuPrint().printError("OpenWeather", "Open weather key is not set");
            Runtime.getRuntime().halt(0);
        }
    }

    @GetMapping("/weather")
    @ResponseBody
    public Response get(@RequestParam String city) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {

            return this.pocasiService.getOpenWeatherData(this.api_key, city);
        }
        return new Response(HttpStatus.TOO_MANY_REQUESTS.value(), null);
    }

}
