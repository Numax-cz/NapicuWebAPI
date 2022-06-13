package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.exception.RequestExceptionSchema;
import com.napicu.napicuwebapi.service.RateLimit;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vše je v pořádku"),
            @ApiResponse(responseCode = "429", description = "Příliš mnoho požadavků",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Chyba databáze",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
        }
    )
    @PostMapping("/popjonanek")
    public NapicuPopJonanekResponseModel setGetCounter(@RequestBody NapicuPopJonanekPostModel ApiData) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            return popJonanekService.updateAndGetCounter(ApiData.counter);
        }
        throw new RequestException(HttpStatus.TOO_MANY_REQUESTS, null);
    }
}
