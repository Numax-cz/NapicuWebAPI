package com.napicu.napicuwebapi.NapicuTypeGame;

import com.fasterxml.jackson.databind.JsonNode;
import com.napicu.napicuwebapi.NapicuIP.NapicuIPResponseModel;
import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.NapicuPrint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLConnection;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class NapicuTypeGameService {


    public Response getWords(int count) {
        try{
            final String url = "http://slova.cetba.eu/generate.php?number=" + count;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String data = responseEntity.getBody();
            byte[] ptext = data.getBytes(ISO_8859_1);
            String value = new String(ptext, UTF_8);
            return  new Response(HttpStatus.OK.value(), value.split(" \\| "));
        }catch (Exception error){
            new NapicuPrint().printError("NapicuIPService", error.toString());
            return  new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }
}
