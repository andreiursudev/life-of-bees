package com.marianbastiurea.lifeofbees.weather;

import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    public WeatherData getWeatherData(BeeTime startDate) {
        String formattedDate = startDate.toFormattedDate();
        String weatherApiUrl = "http://localhost:8081/api/weather/" + formattedDate;
        return restTemplate.getForObject(weatherApiUrl, WeatherData.class);
    }


    public List<WeatherData> getWeatherForNextWeek(BeeTime startDate) {
        String formattedStartingDate = startDate.toFormattedDate();
        BeeTime endDate = startDate.addingDays(6);
        String formattedEndingDate = startDate.toFormattedDate();
        String weatherApiUrl = "http://localhost:8081/api/weather?startDate=" + formattedStartingDate + "&endDate=" + formattedEndingDate;
        WeatherData[] weatherDataArray = restTemplate.getForObject(weatherApiUrl, WeatherData[].class);
        return weatherDataArray != null ? Arrays.asList(weatherDataArray) : new ArrayList<>();
    }
}
