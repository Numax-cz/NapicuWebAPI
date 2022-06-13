package com.napicu.napicuwebapi.NapicuIP;

import com.napicu.napicuwebapi.exception.NapicuExceptions;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.NapicuPrint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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

    public ResponseEntity<NapicuIPResponseModel> getIpInfo() {
        try {
            final String url = "http://ip-api.com/json/" + this.getIp() + "?fields=country,city,org,query";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<NapicuIPResponseModel> responseEntity = restTemplate.getForEntity(url, NapicuIPResponseModel.class);
            return new ResponseEntity<NapicuIPResponseModel>(responseEntity.getBody(), HttpStatus.OK);
        }
        catch (HttpClientErrorException error) {
            throw new RequestException(HttpStatus.BAD_REQUEST, NapicuExceptions.NAPICU_SERVER_ERROR);
        }
    }
}
