package com.marianbastiurea.lifeofbees;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bees")
public class LifeOfBeesController {

    private Map<Integer, LifeOfBees> games;
    private Integer gameId = 0;

    public LifeOfBeesController() {
        this.games = new HashMap<>();  // Inițializare în constructor
    }

    @PostMapping("/game")
    public Integer createGame(@RequestBody GameRequest gameRequest) {
        LifeOfBees lifeOfBeesGame = LifeOfBeesFactory.createLifeOfBeesGame(
                gameId,
                gameRequest.getGameName(),
                gameRequest.getLocation(),
                gameRequest.getStartDate(),
                gameRequest.getNumberOfStartingHives());

        games.put(lifeOfBeesGame.getGameId(), lifeOfBeesGame);

        System.out.println(lifeOfBeesGame);

        gameId++;
        return lifeOfBeesGame.getGameId();
    }

    @GetMapping("/game/{gameId}")
    public GameResponse getGame(@PathVariable Integer gameId) {
        LifeOfBees lifeOfBeesGame = games.get(gameId);

        System.out.println(getGameResponse(lifeOfBeesGame));

        return getGameResponse(lifeOfBeesGame);

    }

    @PostMapping("/iterate/{gameId}")
    public GameResponse iterateGame(@PathVariable Integer gameId) {
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        // lifeOfBeesGame.iterateOneWeek();
        return getGameResponse(lifeOfBeesGame);
    }

    public GameResponse getGameResponse(LifeOfBees game) {
        GameResponse gameResponse = new GameResponse();
        for (Hive hive : game.getApiary().getHives()) {
            gameResponse.getHives().add(new HivesView(hive.getId(), hive.getAgeOfQueen(), hive.getNumberOfBees(), hive.getHoneyType(), hive.getEggsFrames().size(), hive.getHoneyFrames().size(), hive.getKgOfHoney()));
        }
        gameResponse.setTemperature(game.getTemperature());
        gameResponse.setAction(game.getAction().getActionOfTheWeek());
        gameResponse.setWindSpeed(game.getSpeedWind());
        gameResponse.setMoneyInTheBank(game.getMoneyInTheBank());
        gameResponse.setPrecipitation((game.getPrecipitation()));
        gameResponse.setCurrentDate(game.getCurrentDate());
        gameResponse.setTotalKgOfHoney(game.getTotalKgOfHoney());

        return gameResponse;
    }
}
