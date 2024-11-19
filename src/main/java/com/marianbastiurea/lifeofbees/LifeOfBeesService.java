package com.marianbastiurea.lifeofbees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class LifeOfBeesService {

    private final Map<String, WeatherData> allWeatherData = new HashMap<>();

    public WeatherData fetchWeatherForDate(LocalDate date) {
        String weatherApiUrl = "http://localhost:8081/api/weather/" + date;

        try {
            RestTemplate restTemplate = new RestTemplate();
            WeatherData weatherData = restTemplate.getForObject(weatherApiUrl, WeatherData.class);

            if (weatherData != null) {
                allWeatherData.put(date.toString(), weatherData);
                System.out.println("Fetched weather data: " + weatherData);
                return weatherData;
            } else {
                System.err.println("Weather data is null for date: " + date);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error fetching weather data for date " + date + ": " + e.getMessage());
            return null;
        }
    }
}
