package com.napicu.napicuwebapi.NapicuIP;

import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.NapicuPrint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class NapicuIPService {
    HttpServletRequest request;

    NapicuIPService(HttpServletRequest request) {
        this.request = request;
    }

    public String getIp() {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public Response getIpInfo() {
        try {
            final String url = "http://ip-api.com/json/" + this.getIp() + "?fields=country,city,org,query";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<NapicuIPResponseModel> responseEntity = restTemplate.getForEntity(url, NapicuIPResponseModel.class);
            NapicuIPResponseModel data = responseEntity.getBody();
            return new Response(HttpStatus.OK, data);
        } catch (Exception error) {
            new NapicuPrint().printError("NapicuIPService", error.toString());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
