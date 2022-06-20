package com.napicu.napicuwebapi.NapicuBiosWaitList;

import com.napicu.napicuwebapi.NapicuIP.NapicuIPService;
import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekPostModel;
import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekResponseModel;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.exception.RequestExceptionSchema;
import com.napicu.napicuwebapi.service.RateLimit;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.napicu.napicuwebapi.exception.NapicuExceptions.NAPICU_TO_MANY_REQUESTS;

@RestController
public class NapicuBiosWaitListController {

    @Autowired
    private NapicuBiosWaitListService biosService;

    @Autowired
    private RateLimit rateLimit;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vše je v pořádku"),
            @ApiResponse(responseCode = "429", description = "Příliš mnoho požadavků",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
            @ApiResponse(responseCode = "500", description = "Chyba databáze",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RequestExceptionSchema.class))),
    }
    )
    @PostMapping("/waitlist")
    public NapicuBiosWaitListResponseModel post(@RequestBody NapicuBiosWaitListModel data) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            this.biosService.insertEmailToDatabase(data.email);
            NapicuBiosWaitListResponseModel d = new NapicuBiosWaitListResponseModel();
            d.success = true;
            return d;
        }
        throw new RequestException(HttpStatus.TOO_MANY_REQUESTS, NAPICU_TO_MANY_REQUESTS);
    }
}
