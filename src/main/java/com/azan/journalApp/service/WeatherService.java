package com.azan.journalApp.service;

import com.azan.journalApp.api.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisService redisService;

    @Autowired
    private WeatherService weatherService;


    public WeatherResponse getWeather(String city) {
        try {
            WeatherResponse weatherResponse = redisService.get(city, WeatherResponse.class);
            WeatherResponse body;
            if (weatherResponse == null) {
                return weatherResponse;
            } else {
                String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey != null ? apiKey : "");
                ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                        finalAPI, HttpMethod.GET, HttpEntity.EMPTY, WeatherResponse.class
                );
                body = response.getBody();
                if (body != null) {
                    redisService.set("weather_of_" + city, body, 300l);
                }
            }
            return body;
        } catch (Exception e) {
            log.error("Failed to fetch weather for city '{}': {}", city, e.getMessage());
            return null;
        }
    }

}
