package com.marianbastiurea.lifeofbees.weather;

import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    public WeatherData getWeatherData(BeeTime startDate) {
        String weatherApiUrl = "http://localhost:8081/api/weather/" + startDate;
        return restTemplate.getForObject(weatherApiUrl, WeatherData.class);
    }

    public List<WeatherData> getWeatherForNextWeek(BeeTime startDate) {
        BeeTime endDate = startDate.addingDays(6);
        String weatherApiUrl = "http://localhost:8081/api/weather?startDate=" + startDate + "&endDate=" + endDate;
        WeatherData[] weatherDataArray = restTemplate.getForObject(weatherApiUrl, WeatherData[].class);
        return weatherDataArray != null ? Arrays.asList(weatherDataArray) : new ArrayList<>();
    }
}
