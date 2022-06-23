package com.napicu.napicuwebapi.NapicuIP;

import com.napicu.napicuwebapi.exception.NapicuExceptions;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.NapicuPrint;
import org.springdoc.webmvc.core.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class NapicuIPService  {
    HttpServletRequest request;

    NapicuIPService(HttpServletRequest request) {
        this.request = request;
    }

    private final String LOCALHOST_IPV4 = "127.0.0.1";
    private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";


    public String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        if(!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }



    public NapicuIPResponseModel getIpInfo(HttpServletRequest requestService) {
        try {
            final String url = "http://ip-api.com/json/" + this.getClientIp(requestService) + "?fields=country,city,org,query";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<NapicuIPResponseModel> responseEntity = restTemplate.getForEntity(url, NapicuIPResponseModel.class);
            return responseEntity.getBody();
        }
        catch (HttpClientErrorException error) {
            throw new RequestException(HttpStatus.BAD_REQUEST, NapicuExceptions.NAPICU_BAD_IP);
        }
    }
}
