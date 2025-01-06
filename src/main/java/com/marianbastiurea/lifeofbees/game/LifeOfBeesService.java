package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.weather.WeatherData;
import com.marianbastiurea.lifeofbees.users.User;
import com.marianbastiurea.lifeofbees.users.UserService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LifeOfBeesService {

    private final Map<String, WeatherData> allWeatherData = new HashMap<>();

    private final MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private LifeOfBeesRepository lifeOfBeesRepository;

    @Autowired
    public LifeOfBeesService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

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

    public List<WeatherData> fetchWeatherForWeek(LocalDate startDate) {
        System.out.println("Received startDate in LifeOfBeesService in metoda fetchWeatherForWeek: " + startDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = startDate.plusDays(6).format(formatter);
        String weatherApiUrl = "http://localhost:8081/api/weather?startDate=" + formattedStartDate + "&endDate=" + formattedEndDate;
        System.out.println("Generated URL: " + weatherApiUrl);
        try {
            RestTemplate restTemplate = new RestTemplate();
            WeatherData[] weatherDataArray = restTemplate.getForObject(weatherApiUrl, WeatherData[].class);

            if (weatherDataArray != null) {
                List<WeatherData> weatherDataList = Arrays.asList(weatherDataArray);
                weatherDataList.forEach(weatherData -> allWeatherData.put(weatherData.getDate().toString(), weatherData));
                System.out.println("Fetched weather data for week starting " + startDate + ": " + weatherDataList);
                return weatherDataList;
            } else {
                System.err.println("Weather data is null for week starting " + startDate);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            System.err.println("Error fetching weather data for week starting " + startDate + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<LifeOfBees> getGamesForJohnDoe() {
        User user = userService.findUserByUsername("johndoe");
        if (user != null) {
            List<String> gamesList = user.getGamesList();
            return lifeOfBeesRepository.findAllById(gamesList);
        }

        return List.of();
    }

    public List<LifeOfBees> getGamesForUserByType(String userId, String gameType) {
        System.out.println("acesta e userId in getGamesForUserByType: " + userId);
        System.out.println("acesta e gameType in getGamesForUserByType: " + gameType);
        User user = userService.findUserByUserId(userId);

        if (user != null) {
            List<String> gamesList = user.getGamesList();
            if (gamesList.isEmpty()) {
                return List.of();
            }

            List<LifeOfBees> userGames = lifeOfBeesRepository.findAllById(gamesList);
            System.out.println("aceasta e lista de jocuri din getGamesForUserByType: " + userGames);

            return userGames.stream()
                    .filter(game -> gameType.equals(game.getGameType()))
                    .collect(Collectors.toList());
        }

        return List.of();
    }

}
