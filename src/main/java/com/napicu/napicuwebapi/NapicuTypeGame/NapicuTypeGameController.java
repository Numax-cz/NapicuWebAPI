package com.napicu.napicuwebapi.NapicuTypeGame;


import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.RateLimit;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NapicuTypeGameController {
    @Autowired
    private NapicuTypeGameService typeGameService;
    @Autowired
    private RateLimit rateLimit;


    @ApiResponses(
            @ApiResponse(responseCode = "500")
    )
    @GetMapping("/words")
    @ResponseBody
    public ResponseEntity<String> setGetCounter(@RequestParam String count) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
                return typeGameService.getWords(Integer.parseInt(count));
        }
        throw new RequestException(HttpStatus.TOO_MANY_REQUESTS, null);
    }
}
