package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.users.User;
import com.marianbastiurea.lifeofbees.users.UserService;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<LifeOfBees> getGamesForJohnDoe() {
        User user = userService.findUserByUsername("johndoe");
        if (user != null) {
            List<String> gamesList = user.getGamesList();
            return lifeOfBeesRepository.findAllById(gamesList);
        }
        return List.of();
    }

    public List<LifeOfBees> getGamesForUserByType(String userId, String gameType) {
        User user = userService.findUserByUserId(userId);
        if (user != null) {
            List<String> gamesList = user.getGamesList();
            if (gamesList.isEmpty()) {
                return List.of();
            }
            List<LifeOfBees> userGames = lifeOfBeesRepository.findAllById(gamesList);
            return userGames.stream()
                    .filter(game -> gameType.equals(game.getGameType()))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

}
