package com.napicu.napicuwebapi.NapicuTypeGame;

import com.napicu.napicuwebapi.Response.ResponseHandler;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.RateLimit;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NapicuTypeGameController {
    @Autowired
    private NapicuTypeGameService typeGameService;
    @Autowired
    private RateLimit rateLimit;


    @GetMapping("/words")
    @ResponseBody
    public ResponseEntity<Object> setGetCounter(@RequestParam String count) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            try{
                String[] data = typeGameService.getWords(Integer.parseInt(count));
                return ResponseHandler.Response(HttpStatus.OK, data);
            }catch (RequestException error){
                return ResponseHandler.ResponseError(error.status, error.code.value());
            }
        }
        return ResponseHandler.Response(HttpStatus.TOO_MANY_REQUESTS, null);
    }
}
