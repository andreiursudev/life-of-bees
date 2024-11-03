package com.marianbastiurea.lifeofbees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.marianbastiurea.lifeofbees.Honey.getHarvestingMonth;

@RestController
@RequestMapping("/api/bees")
public class LifeOfBeesController {

    private Map<Integer, LifeOfBees> games;
    private Integer gameId = 0;

    public LifeOfBeesController() {
        this.games = new HashMap<>();
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
        gameId++;
        return lifeOfBeesGame.getGameId();
    }

    @GetMapping("/game/{gameId}")
    public GameResponse getGame(@PathVariable Integer gameId) {
        LifeOfBees lifeOfBeesGame = games.get(gameId);

        System.out.println("acestea sunt datele trimise catre React: " + getGameResponse(lifeOfBeesGame));

        return getGameResponse(lifeOfBeesGame);
    }

    @PostMapping("/iterate/{gameId}")
    public GameResponse iterateGame(@PathVariable Integer gameId) {
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        lifeOfBeesGame = lifeOfBeesGame.iterateOneWeek(lifeOfBeesGame);
        GameResponse response = getGameResponse(lifeOfBeesGame);
        System.out.println("GameResponse after iteration: " + response); // Adaugă acest log
        return response;
    }

    @PostMapping("/submitActionsOfTheWeek/{gameId}")
    public GameResponse submitActionsOfTheWeek(@PathVariable Integer gameId, @RequestBody List<ActionOfTheWeek> approvedActions) {
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        Apiary apiary = lifeOfBeesGame.getApiary();
        LocalDate date = LocalDate.parse(lifeOfBeesGame.getCurrentDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        HarvestingMonths month = getHarvestingMonth(date);
        int dayOfMonth = date.getDayOfMonth();
        for (ActionOfTheWeek action : approvedActions) {
            switch (action.getActionType()) {
                case "ADD_EGGS_FRAME":
                    List<Integer> eggHiveIds = (List<Integer>) action.getData().get("hiveIds");
                    if (eggHiveIds != null) {
                        for (Integer hiveId : eggHiveIds) {
                            Hive hive = apiary.getHiveById(hiveId);
                            hive.addNewEggsFrameInHive();
                        }
                    }
                    break;

                case "ADD_HONEY_FRAME":
                    List<Integer> honeyHiveIds = (List<Integer>) action.getData().get("hiveIds");
                    if (honeyHiveIds != null) {
                        for (Integer hiveId : honeyHiveIds) {
                            Hive hive = apiary.getHiveById(hiveId);
                            if (hive != null) {
                                hive.addNewHoneyFrameInHive();
                            }
                        }
                    }
                    break;

                case "MOVE_EGGS_FRAME":
                    List<List<Integer>> hiveIdPairs = (List<List<Integer>>) action.getData().get("hiveIdPairs");
                    if (hiveIdPairs != null) {
                        apiary.moveAnEggsFrame(hiveIdPairs);
                    }
                    break;

                case "FEED_BEES":
                    Map<String, Object> feedBeesData = (Map<String, Object>) action.getData();
                    String feedBeesAnswer = (String) feedBeesData.get("answer");
                    apiary.doFeedBees(feedBeesAnswer, lifeOfBeesGame);
                    break;

                case "SPLIT_HIVE":
                    List<Integer> splitHiveIds = (List<Integer>) action.getData().get("hiveIds");
                    if (splitHiveIds != null) {
                        for (Integer hiveId : splitHiveIds) {
                            Hive hive = apiary.getHiveById(hiveId);
                            if (hive != null) {
                                apiary.splitHive(hive);
                            }
                        }
                    }
                    break;

                case "INSECT_CONTROL":
                    Map<String, Object> insectControlData = (Map<String, Object>) action.getData();
                    String insectControlResponse = (String) insectControlData.get("answer");
                    apiary.doInsectControl(insectControlResponse, lifeOfBeesGame);
                    break;

                default:
                    System.out.println("Unknown action type: " + action.getActionType());
                    break;
            }
        }
        lifeOfBeesGame.getActionOfTheWeek().clear();
        GameResponse response = getGameResponse(lifeOfBeesGame);
        System.out.println("GameResponse after submit action of the week: " + response);
        return response;
    }

    public GameResponse getGameResponse(LifeOfBees game) {
        GameResponse gameResponse = new GameResponse();
        for (Hive hive : game.getApiary().getHives()) {
            gameResponse.getHives().add(new HivesView(hive.getId(), hive.getAgeOfQueen(), hive.getNumberOfBees(), hive.getHoneyType(), hive.getEggsFrames().size(), hive.getHoneyFrames().size(), hive.getKgOfHoney(), hive.isItWasSplit()));
        }
        gameResponse.setTemperature(game.getTemperature());
        gameResponse.setActionOfTheWeek(game.getActionOfTheWeek());
        gameResponse.setWindSpeed(game.getSpeedWind());
        gameResponse.setMoneyInTheBank(game.getMoneyInTheBank());
        gameResponse.setPrecipitation(game.getPrecipitation());
        gameResponse.setCurrentDate(game.getCurrentDate());
        gameResponse.setTotalKgOfHoneyHarvested(game.getTotalKgOfHoneyHarvested());

        return gameResponse;
    }


    @GetMapping("/getHoneyQuantities/{gameId}")
    public ResponseEntity<Map<String, Object>> getHoneyQuantities(@PathVariable Integer gameId) {
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        Apiary apiary = lifeOfBeesGame.getApiary();
        Map<String, Object> honeyData = apiary.getTotalHarvestedHoney();
        return ResponseEntity.ok(honeyData);
    }


    @PostMapping("/sellHoney/{gameId}")
    public ResponseEntity<String> sendSellHoneyQuantities(
            @PathVariable Integer gameId,
            @RequestBody Map<String, Object> requestData) {
        Map<String, Object> soldHoneyData = (Map<String, Object>) requestData.get("soldData");
        System.out.println(soldHoneyData);
        double revenue = Double.parseDouble((String) requestData.get("totalValue"));
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        Apiary apiary = lifeOfBeesGame.getApiary();
        apiary.updateHoneyStock(soldHoneyData);
        lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() + revenue);

        return ResponseEntity.ok("Stock and revenue updated successfully.");
    }

    @PostMapping("/buyHives/{gameId}")
    public ResponseEntity<?> buyHives(@PathVariable Integer gameId, @RequestBody Map<String, Object> request) {
        Integer numberOfHives = Integer.parseInt((String) request.get("numberOfHives"));
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        Apiary apiary = lifeOfBeesGame.getApiary();
        apiary.createHive(numberOfHives);
        lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() - numberOfHives * 500);

        return ResponseEntity.ok("Hives bought successfully");
    }

}
