package com.azan.journalApp.service;

import com.azan.journalApp.api.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherService {

    @Value("${weather.api.key:}")
    private String apiKey;
    private static final String API = "https://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY";

    @Autowired
    private RestTemplate restTemplate;


    public WeatherResponse getWeather(String city) {
        try {
            String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey != null ? apiKey : "");
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                    finalAPI,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    WeatherResponse.class
            );
            return response.getBody();
        } catch (Exception e) {
            log.error("Failed to fetch weather for city '{}': {}", city, e.getMessage());
            return null;
        }
    }

}
