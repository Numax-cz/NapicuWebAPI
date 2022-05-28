package com.napicu.napicuwebapi.NapicuIP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

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

    public NapicuIPModel getIpInfo(){
        final String url = "http://ip-api.com/json/" + "46.33.112.72" + "?fields=country,city,org,query";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NapicuIPModel> responseEntity = restTemplate.getForEntity(url, NapicuIPModel.class);
        NapicuIPModel data = responseEntity.getBody();
        return  data;
    }
}
