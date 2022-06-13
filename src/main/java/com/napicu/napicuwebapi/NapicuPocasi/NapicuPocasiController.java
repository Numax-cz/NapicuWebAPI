package com.napicu.napicuwebapi.NapicuPocasi;


import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.exception.RequestExceptionSchema;
import com.napicu.napicuwebapi.service.NapicuPrint;
import com.napicu.napicuwebapi.service.RateLimit;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vše je v pořádku",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
            @ApiResponse(responseCode = "429", description = "Příliš mnoho požadavků",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
            @ApiResponse(responseCode = "400", description = "Nebylo možné nalést požadované město",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Chyba serveru",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class)))
        }
    )
    @GetMapping("/weather")
    @ResponseBody
    public NapicuPocasiResponseModel get(@RequestParam String city) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            return this.pocasiService.getOpenWeatherData(this.api_key, city);
        }
        throw new RequestException(HttpStatus.TOO_MANY_REQUESTS, null);
    }
}
