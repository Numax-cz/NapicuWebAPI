package com.napicu.napicuwebapi.NapicuBiosWaitList;

import com.napicu.napicuwebapi.NapicuIP.NapicuIPService;
import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekPostModel;
import com.napicu.napicuwebapi.NapicuPopJonanek.NapicuPopJonanekResponseModel;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.RateLimit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NapicuBiosWaitListController {

    @Autowired
    private NapicuBiosWaitListService biosService;

    @Autowired
    private RateLimit rateLimit;


    @PostMapping("/waitlist")
    public NapicuBiosWaitListResponseModel post(@RequestBody NapicuBiosWaitListModel data) {
        if (rateLimit.getServiceBucket().tryConsume(1)) {
            this.biosService.insertEmailToDatabase(data.email);
            NapicuBiosWaitListResponseModel d = new NapicuBiosWaitListResponseModel();
            d.success = true;
            return d;
        }
        throw new RequestException(HttpStatus.TOO_MANY_REQUESTS, null);
    }
}
