package com.napicu.napicuwebapi.NapicuIP;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NapicuIPController {
    @Autowired
    private NapicuIPService ipService;

    @Autowired
    private RateLimit rateLimit;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vše je v pořádku"),
            @ApiResponse(responseCode = "429", description = "Příliš mnoho požadavků",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
            @ApiResponse(responseCode = "400", description = "Nebylo možné získat informace o IP adrese",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
        }
    )
    @GetMapping("/ip")
    public NapicuIPResponseModel get() {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            return ipService.getIpInfo();
        }
        throw new RequestException(HttpStatus.TOO_MANY_REQUESTS, null);
    }
}
