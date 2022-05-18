package com.napicu.napicuwebapi.NapicuIP;

import com.napicu.napicuwebapi.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.URLConnection;

@Service
public class NapicuIPService {


    HttpServletRequest request;

    NapicuIPService(HttpServletRequest request){
        this.request = request;
    }


    public String getIp(){
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }


    public String getIpInfo(){
        final String uri = "http://ip-api.com/json/" + getIp() + "?fields=country,city,org,query";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }






}
