package com.napicu.napicuwebapi.NapicuPocasi;

import com.fasterxml.jackson.databind.JsonNode;
import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.NapicuPrint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NapicuPocasiService {

    public Response getOpenWeatherData(String api_key, String country) {
        NapicuPocasiResponseModel data = new NapicuPocasiResponseModel();
        try {
            final String url = "http://api.openweathermap.org/data/2.5/weather?q=" + country + "&units=metric&appid=" + api_key + "&lang=cz";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(url, JsonNode.class);

            JsonNode response = responseEntity.getBody();

            data.name = response.get("name").asText();
            data.country = response.get("sys").get("country").asText();
            data.pressure = (int) Float.parseFloat(response.get("main").get("pressure").toString());
            data.temp = (int) Float.parseFloat(response.get("main").get("temp").toString());
            data.temp_min = (int) Float.parseFloat(response.get("main").get("temp_min").toString());
            data.temp_max = (int) Float.parseFloat(response.get("main").get("temp_max").toString());
            data.feels_like = (int) Float.parseFloat(response.get("main").get("feels_like").toString());
            data.humidity = (int) Float.parseFloat(response.get("main").get("humidity").toString());
            data.clouds = (int) Float.parseFloat(response.get("clouds").get("all").toString());
            data.wind_speed = (int) Float.parseFloat(response.get("wind").get("speed").toString());
            data.icon = response.get("weather").get(0).get("icon").asText();
            data.description = response.get("weather").get(0).get("description").asText();
        } catch (Exception error) {
            new NapicuPrint().printError("NapicuPocasiService", error.toString());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
        return new Response(HttpStatus.OK.value(), data);
    }
}
