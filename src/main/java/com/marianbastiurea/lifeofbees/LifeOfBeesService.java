package com.marianbastiurea.lifeofbees;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LifeOfBeesService {

    private final Map<String, WeatherData> allWeatherData = new HashMap<>();

    private final MongoTemplate mongoTemplate;

    @Autowired
    public LifeOfBeesService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addToGameHistory(LifeOfBees lifeOfBeesGame) {
        System.out.println("Obiectul lifeOfBees în metoda addToHistory: " + lifeOfBeesGame);

        Query query = Query.query(Criteria.where("userId").is(lifeOfBeesGame.getUserId())
                .and("gameName").is(lifeOfBeesGame.getGameName()));
        System.out.println("Query creat în metoda addToHistory: " + query);

        LifeOfBees existingGame = mongoTemplate.findOne(query, LifeOfBees.class);

        if (existingGame != null) {
            List<LifeOfBees> existingGameHistory = existingGame.getGameHistory();
            if (existingGameHistory == null) {
                existingGameHistory = new ArrayList<>();
            }
            existingGameHistory.add(lifeOfBeesGame);
            Update update = new Update().set("Game history", existingGameHistory);

            System.out.println("Update creat în metoda addToGameHistory: " + update);

            var result = mongoTemplate.updateFirst(query, update, LifeOfBees.class);
            System.out.println("Rezultat actualizare în metoda addToGameHistory: " + result);
        } else {
            System.out.println("Jocul nu a fost găsit pentru actualizare în metoda addToGameHistory.");
        }
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
}
