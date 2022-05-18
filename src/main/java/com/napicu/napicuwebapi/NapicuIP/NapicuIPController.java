package com.napicu.napicuwebapi.NapicuIP;


import com.napicu.napicuwebapi.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
@RestController
public class NapicuIPController {
    @Autowired
    private NapicuIPService ipService;


    @GetMapping("/ip")
    public Response get(){
        return new Response(true, ipService.getIpInfo());
    }

}
