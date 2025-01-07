package com.marianbastiurea.lifeofbees.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    public WeatherData getWeatherData(LocalDate startDate) {
        String weatherApiUrl = "http://localhost:8081/api/weather/" + startDate;
        return restTemplate.getForObject(weatherApiUrl, WeatherData.class);
    }

    public List<WeatherData> getWeatherForNextWeek(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(6);
        String weatherApiUrl = "http://localhost:8081/api/weather?startDate=" + startDate + "&endDate=" + endDate;
        WeatherData[] weatherDataArray = restTemplate.getForObject(weatherApiUrl, WeatherData[].class);
        return weatherDataArray != null ? Arrays.asList(weatherDataArray) : new ArrayList<>();
    }
}
