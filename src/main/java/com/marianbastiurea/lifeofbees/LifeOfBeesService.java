package com.marianbastiurea.lifeofbees;

import com.marianbastiurea.lifeofbees.Users.User;
import com.marianbastiurea.lifeofbees.Users.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LifeOfBeesService {

    private final Map<String, WeatherData> allWeatherData = new HashMap<>();

    private final MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LifeOfBeesRepository lifeOfBeesRepository;

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

    public List<LifeOfBees> getGamesForJohnDoe() {
        Optional<User> userOpt = userRepository.findByUsername("johndoe");

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<String> gamesList = user.getGamesList();
            List<LifeOfBees> userGames = lifeOfBeesRepository.findAllById(gamesList);
            return userGames;
        } else {
            return List.of();
        }
    }

    public List<LifeOfBees> getGamesForUserByType(String userId, String gameType) {
        Optional<User> userOpt = userRepository.findByUserId(userId);
        System.out.println("acesta e userId in getGamesForUserByType: " + userId);
        System.out.println("acesta e gameType in getGamesForUserByType: " + gameType);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<String> gamesList = user.getGamesList();
            if (gamesList.isEmpty()) {
                return List.of();
            }
            List<LifeOfBees> userGames = lifeOfBeesRepository.findAllById(gamesList);
            System.out.println("aceasta e lista de jocuri din getGamesForUserByType: "+userGames);

            return userGames.stream()
                    .filter(game -> gameType.equals(game.getGameType()))
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

}
