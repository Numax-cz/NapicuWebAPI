package com.napicu.napicuwebapi.NapicuIP;


import com.napicu.napicuwebapi.Response.ResponseHandler;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.RateLimit;
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

    @GetMapping("/ip")
    public ResponseEntity<Object> get() {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            try{
                NapicuIPResponseModel data =  ipService.getIpInfo();
                return ResponseHandler.Response(HttpStatus.OK, data);
            }catch (RequestException error){
                return ResponseHandler.ResponseError(error.status, error.code.value());
            }
        }
        return ResponseHandler.ResponseError(HttpStatus.TOO_MANY_REQUESTS);
    }

}
