package com.marianbastiurea.lifeofbees;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

        System.out.println( "acestea sunt datele trimise catre React: "+   getGameResponse( lifeOfBeesGame));

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
    public GameResponse submitApprovedHives(@PathVariable Integer gameId, @RequestBody List<ActionOfTheWeek> approvedActions) {
        LifeOfBees lifeOfBeesGame = games.get(gameId);
        Apiary apiary = lifeOfBeesGame.getApiary();

        LocalDate date = LocalDate.parse(lifeOfBeesGame.getCurrentDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        HarvestingMonths month = getHarvestingMonth(date);
        int dayOfMonth = date.getDayOfMonth();

        for (ActionOfTheWeek action : approvedActions) {
            List<Integer> approvedHiveIds = action.getHiveIds();
            System.out.println("Processing action: " + action.getActionOfTheWeekMarker());

            // Verificăm acțiunea
            switch (action.getActionOfTheWeekMarker()) {
                case "MOVE_EGGS_FRAME":
                    // Pentru acțiunea MOVE_EGGS_FRAME, verificăm dacă lista de stupi conține exact 2 ID-uri
                    if (approvedHiveIds.size() != 2) {
                        throw new IllegalArgumentException("MOVE_EGGS_FRAME action requires exactly two hive IDs: source and destination.");
                    }

                    // Extragem stupul sursă și stupul destinație
                    Hive sourceHive = apiary.getHiveById(approvedHiveIds.get(0));
                    Hive destinationHive = apiary.getHiveById(approvedHiveIds.get(1));

                    // Verificăm dacă ambele stupi există
                    if (sourceHive == null || destinationHive == null) {
                        throw new IllegalArgumentException("Invalid hive IDs provided for MOVE_EGGS_FRAME.");
                    }

                    // Apelăm metoda de mutare a ramei de ouă
                    sourceHive.moveAnEggsFrameFromUnsplitHiveToASplitOne(List.of(approvedHiveIds));
                    System.out.println("Moved eggs frame from Hive " + approvedHiveIds.get(0) + " to Hive " + approvedHiveIds.get(1));
                    break;

                default:
                    // Procesăm celelalte acțiuni
                    for (Integer hiveId : approvedHiveIds) {
                        Hive hive = apiary.getHiveById(hiveId);
                        if (hive != null) {
                            switch (action.getActionOfTheWeekMarker()) {
                                case "ADD_NEW_EGGS_FRAME":
                                    hive.addNewEggsFrameInHive();
                                    break;
                                case "ADD_NEW_HONEY_FRAME":
                                    hive.addNewHoneyFrameInHive();
                                    break;
                                case "SPLIT_HIVE":
                                    hive.checkIfHiveCouldBeSplit(month, dayOfMonth);
                                    apiary.splitHive(hive);
                                    break;
                                case "COLLECT_HONEY":
                                    // Implementarea pentru colectare
                                    break;
                                case "HIBERNATE":
                                    // Implementarea pentru hibernare
                                    break;
                                case "INSECT_CONTROL":
                                    // Implementarea pentru controlul insectelor
                                    break;
                                default:
                                    System.out.println("Unknown action marker: " + action.getActionOfTheWeekMarker());
                                    break;
                            }
                        } else {
                            System.out.println("Hive not found with ID: " + hiveId);
                        }
                    }
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
}
