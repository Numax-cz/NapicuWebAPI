package com.napicu.napicuwebapi.NapicuTypeGame;


import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.exception.RequestExceptionSchema;
import com.napicu.napicuwebapi.service.RateLimit;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class NapicuTypeGameController {
    @Autowired
    private NapicuTypeGameService typeGameService;
    @Autowired
    private RateLimit rateLimit;



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vše je v pořádku",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
            @ApiResponse(responseCode = "429", description = "Příliš mnoho požadavků",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Nebylo možné získat slova",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
        }
    )
    @GetMapping("/words")
    @ResponseBody
    public String setGetCounter(@RequestParam String count) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
                return typeGameService.getWords(Integer.parseInt(count));
        }
        throw new RequestException(HttpStatus.TOO_MANY_REQUESTS, null);
    }
}
