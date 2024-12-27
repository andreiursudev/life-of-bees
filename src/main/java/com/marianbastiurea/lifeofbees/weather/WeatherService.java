package com.marianbastiurea.lifeofbees.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class WeatherService {
    @Autowired
    private static RestTemplate restTemplate;

    public static WeatherData getWeatherData(LocalDate startDate) {
        String weatherApiUrl = "http://localhost:8081/api/weather/" + startDate;
        System.out.println("acesta e weatherAPI url:"+weatherApiUrl);
        WeatherData weatherData = restTemplate.getForObject(weatherApiUrl, WeatherData.class);
        return weatherData;
    }
}
