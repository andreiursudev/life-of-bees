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
                gameRequest.getName(),
                gameRequest.getLocation(),
                gameRequest.getStartDate(),
                gameRequest.getNumberOfStartingHives());

        games.put(lifeOfBeesGame.getGameId(), lifeOfBeesGame);
        gameId++;

        System.out.println("Game created with ID: " + lifeOfBeesGame.getGameId());
        System.out.println("Name: " + lifeOfBeesGame.getName());
        System.out.println("Location: " + lifeOfBeesGame.getLocation());
        System.out.println("Start Date: " + lifeOfBeesGame.getStartingDate());
        System.out.println("Number of hives: " + lifeOfBeesGame.getApiary().getHives().size());
        System.out.println("Hives: " + lifeOfBeesGame.getApiary().getHives());



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
        gameResponse.setTemp(game.getTemperature());
        gameResponse.setAction(game.getAction().getActionOfTheWeek());
        gameResponse.setWindSpeed(game.getSpeedWind());
        gameResponse.setMoneyInTheBank(game.getMoneyInTheBank());

        return gameResponse;
    }
}
