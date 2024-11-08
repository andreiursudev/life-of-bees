package com.marianbastiurea.lifeofbees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


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
        System.out.println(" acestea sunt datele de start: "+lifeOfBeesGame);
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
        LocalDate date = lifeOfBeesGame.getCurrentDate();
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
            gameResponse.getHives().add(new HivesView(hive.getId(), hive.getAgeOfQueen(), hive.getNumberOfBees(), hive.getEggsFrames().size(), hive.getHoneyFrames().size(), hive.getKgOfHoney(), hive.isItWasSplit()));
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
    public ResponseEntity<HarvestHoney> getHoneyQuantities(@PathVariable Integer gameId) {
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        Apiary apiary = lifeOfBeesGame.getApiary();
        apiary.honeyHarvestedByHoneyType();
        System.out.println(" acesta e geterul din apiary:"+apiary.getTotalHarvestedHoney());
        HarvestHoney honeyData = apiary.getTotalHarvestedHoney();
        System.out.println("aceasta e mierea trimisa catre SellHoney.js: " +honeyData);
        return ResponseEntity.ok(honeyData);
    }


    @PostMapping("/sellHoney/{gameId}")
    public ResponseEntity<String> sendSellHoneyQuantities(
            @PathVariable Integer gameId,
            @RequestBody Map<String, Double> requestData) {
        System.out.println("cantitatile de miere vandute au sosit");

        double revenue = requestData.getOrDefault("totalValue", 0.0);
        System.out.println("banii incasati sunt: " + revenue);

        HarvestHoney soldHoneyData = new HarvestHoney();
        System.out.println("mierea vanduta este: " + soldHoneyData);


        for (Map.Entry<String, Double> entry : requestData.entrySet()) {
            if (!entry.getKey().equals("totalValue")) {
                switch (entry.getKey().toLowerCase()) {
                    case "acacia":
                        soldHoneyData.Acacia = entry.getValue();
                        break;
                    case "rapeseed":
                        soldHoneyData.Rapeseed = entry.getValue();
                        break;
                    case "wildflower":
                        soldHoneyData.WildFlower = entry.getValue();
                        break;
                    case "linden":
                        soldHoneyData.Linden = entry.getValue();
                        break;
                    case "sunflower":
                        soldHoneyData.SunFlower = entry.getValue();
                        break;
                    case "falseindigo":
                        soldHoneyData.FalseIndigo = entry.getValue();
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Invalid honey type: " + entry.getKey());
                }
            }
        }

        LifeOfBees lifeOfBeesGame = games.get(gameId);
        Apiary apiary = lifeOfBeesGame.getApiary();


        apiary.updateHoneyStock(soldHoneyData);
        lifeOfBeesGame.setTotalKgOfHoneyHarvested(apiary.getTotalKgHoneyHarvested());
        lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() + revenue);

        return ResponseEntity.ok("Stock and revenue updated successfully.");
    }


    @PostMapping("/buyHives/{gameId}")
    public ResponseEntity<?> buyHives(@PathVariable Integer gameId, @RequestBody Map<String, Integer> request) {
        Integer numberOfHives = request.get("numberOfHives"); // Fără conversie, fiind deja Integer
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        Apiary apiary = lifeOfBeesGame.getApiary();
        Hive.addHivesToApiary(apiary, apiary.createHive(numberOfHives, lifeOfBeesGame.getCurrentDate()));
        lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() - numberOfHives * 500);
        return ResponseEntity.ok("Hives bought successfully");
    }

}
