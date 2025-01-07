package com.marianbastiurea.lifeofbees.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.HarvestHoney;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.game.LifeOfBeesFactory;
import com.marianbastiurea.lifeofbees.game.LifeOfBeesRepository;
import com.marianbastiurea.lifeofbees.game.LifeOfBeesService;
import com.marianbastiurea.lifeofbees.history.ApiaryHistory;
import com.marianbastiurea.lifeofbees.history.GameHistory;
import com.marianbastiurea.lifeofbees.history.GameHistoryService;
import com.marianbastiurea.lifeofbees.history.HiveHistory;
import com.marianbastiurea.lifeofbees.security.JwtTokenProvider;
import com.marianbastiurea.lifeofbees.users.User;
import com.marianbastiurea.lifeofbees.users.UserService;
import com.marianbastiurea.lifeofbees.view.GameRequest;
import com.marianbastiurea.lifeofbees.view.GameResponse;
import com.marianbastiurea.lifeofbees.view.HivesView;
import com.marianbastiurea.lifeofbees.view.HomePageGameResponse;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import com.marianbastiurea.lifeofbees.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/bees")
public class LifeOfBeesController {
    private final LifeOfBeesRepository lifeOfBeesRepository;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final GameHistoryService gameHistoryService;

    @Autowired
    private LifeOfBeesService lifeOfBeesService;
    @Autowired
    private WeatherService weatherService;


    public LifeOfBeesController(LifeOfBeesRepository lifeOfBeesRepository,
                                UserService userService, JwtTokenProvider jwtTokenProvider,
                                GameHistoryService gameHistoryService) {
        this.lifeOfBeesRepository = lifeOfBeesRepository;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.gameHistoryService = gameHistoryService;
    }

    private static void accessDenied(LifeOfBees lifeOfBeesGame, String userId) {
        if (!lifeOfBeesGame.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }

    private Optional<LifeOfBees> getByGameId(String gameId) {
        return lifeOfBeesRepository.findByGameId(gameId);
    }

    @PostMapping("/game")
    public ResponseEntity<Map<String, String>> createGame(@RequestBody GameRequest gameRequest, @RequestHeader("Authorization") String authorizationHeader) {
        System.out.println("Received request to create game: " + gameRequest);
        String jwtToken = authorizationHeader.replace("Bearer ", "");
        LocalDate startDate = LocalDate.parse(gameRequest.getStartDate());
        WeatherData weatherData = weatherService.getWeatherData(startDate);
        String userIdFromToken;
        try {
            userIdFromToken = jwtTokenProvider.extractUserId(jwtToken);
            System.out.println("userId din Token" + userIdFromToken);
        } catch (Exception e) {
            System.out.println("An error occurred while extracting username: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid token"));
        }
        if (!userIdFromToken.equals(gameRequest.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Token does not match userId in createGame"));
        }
        User user = userService.getUser(gameRequest);

        LifeOfBees lifeOfBeesGame = LifeOfBeesFactory.createLifeOfBeesGame(
                gameRequest.getGameName(),
                gameRequest.getLocation(),
                gameRequest.getStartDate(),
                gameRequest.getNumberOfStartingHives(),
                gameRequest.getUserId(),
                gameRequest.getGameType(),
                weatherData
        );

        LifeOfBees savedGame = lifeOfBeesRepository.save(lifeOfBeesGame);
        userService.addGameToUser(user, lifeOfBeesGame.getGameId());
        gameHistoryService.saveGameHistory(savedGame);
        Map<String, String> response = new HashMap<>();
        response.put("token", jwtToken);
        response.put("gameId", savedGame.getGameId());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/game/{gameId}")
    public GameResponse getGame(@PathVariable String gameId, Principal principal) {
        System.out.println("request for gameId: " + gameId);
        LifeOfBees lifeOfBeesGame = getByGameId(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        String userId = principal.getName();
        accessDenied(lifeOfBeesGame, userId);
        GameResponse response = getGameResponse(lifeOfBeesGame);
        System.out.println("Data send to React: " + response);
        return response;
    }


    @PostMapping("/iterate/{gameId}")
    public GameResponse iterateWeek(
            @PathVariable String gameId,
            @RequestBody Map<String, Object> requestData,
            Principal principal) {
        System.out.println("Request for iterate gameId: " + gameId);
        LifeOfBees lifeOfBeesGame = getByGameId(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        List<WeatherData> weatherDataNextWeek = weatherService.getWeatherForNextWeek(lifeOfBeesGame.getCurrentDate());
        if (weatherDataNextWeek.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weather data for the next week not found");
        }
        String userId = principal.getName();
        accessDenied(lifeOfBeesGame, userId);
        Object data = requestData.get("actions");
        lifeOfBeesGame = lifeOfBeesGame.iterateOneWeek(lifeOfBeesGame, data, weatherDataNextWeek);
        lifeOfBeesRepository.save(lifeOfBeesGame);
        GameResponse response = getGameResponse(lifeOfBeesGame);
        gameHistoryService.addGameInGameHistory(lifeOfBeesGame);
        return response;
    }

    public GameResponse getGameResponse(LifeOfBees game) {
        GameResponse gameResponse = new GameResponse();
        gameResponse.setId(game.getGameId());
        for (Hive hive : game.getApiary().getHives()) {
            gameResponse.getHives().add(new HivesView(hive.getId(), hive.getAgeOfQueen(), hive.getEggFrames().getNumberOfEggFrames(), hive.getHoneyFrames().getHoneyFrame().size(), hive.isItWasSplit()));
        }
        gameResponse.setTemperature(game.getWeatherData().getTemperature());
        gameResponse.setActionsOfTheWeek(game.getActionsOfTheWeek());
        gameResponse.setWindSpeed(game.getWeatherData().getWindSpeed());
        gameResponse.setMoneyInTheBank(game.getMoneyInTheBank());
        gameResponse.setPrecipitation(game.getWeatherData().getPrecipitation());
        gameResponse.setCurrentDate(game.getCurrentDate());
        gameResponse.setTotalKgOfHoneyHarvested(game.getTotalKgOfHoneyHarvested());
        System.out.println("data send to React: " + gameResponse);
        return gameResponse;
    }


    @GetMapping("/getHoneyQuantities/{gameId}")
    public ResponseEntity<HarvestHoney> getHoneyQuantities(@PathVariable String gameId, Principal principal) {
        System.out.println("request for getHoneyQuantities, gameId: " + gameId);
        LifeOfBees lifeOfBeesGame = getByGameId(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        System.out.println("Acesta e jocul primit in getHoney:" + lifeOfBeesGame);
        String userId = principal.getName();
        accessDenied(lifeOfBeesGame, userId);
        Apiary apiary = lifeOfBeesGame.getApiary();
        apiary.honeyHarvestedByHoneyType();
        HarvestHoney honeyData = apiary.getTotalHarvestedHoney();
        return ResponseEntity.ok(honeyData);
    }

    @PostMapping("/sellHoney/{gameId}")
    public ResponseEntity<String> sendSellHoneyQuantities(
            @PathVariable String gameId,
            @RequestBody Map<String, Double> requestData,
            Principal principal) {
        System.out.println("request for selling honey, gameId: " + gameId);
        LifeOfBees lifeOfBeesGame = getByGameId(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        String userId = principal.getName();
        accessDenied(lifeOfBeesGame, userId);
        double revenue = requestData.getOrDefault("totalValue", 0.0);
        HarvestHoney soldHoneyData = new HarvestHoney();

        for (Map.Entry<String, Double> entry : requestData.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("totalValue")) {
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
                        System.out.println("Tip de miere invalid: " + entry.getKey());
                        return ResponseEntity.badRequest().body("Invalid honey type: " + entry.getKey());
                }
            }
        }
        Apiary apiary = lifeOfBeesGame.getApiary();
        apiary.updateHoneyStock(soldHoneyData);
        lifeOfBeesGame.setTotalKgOfHoneyHarvested(apiary.getTotalKgHoneyHarvested());
        lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() + revenue);
        lifeOfBeesRepository.save(lifeOfBeesGame);
        return ResponseEntity.ok("Stock and revenue updated successfully.");
    }


    @PostMapping("/buyHives/{gameId}")
    public ResponseEntity<String> buyHives(
            @PathVariable String gameId,
            @RequestBody Map<String, Integer> request,
            Principal principal) {
        System.out.println("Request to buy hives, gameId: " + gameId);
        LifeOfBees lifeOfBeesGame = getByGameId(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        String userId = principal.getName();
        accessDenied(lifeOfBeesGame, userId);
        Integer numberOfHives = request.get("numberOfHives");
        if (numberOfHives == null || numberOfHives <= 0) {
            return ResponseEntity.badRequest().body("Invalid number of hives.");
        }
        Apiary apiary = lifeOfBeesGame.getApiary();
        apiary.addHivesToApiary(apiary.createHive(numberOfHives, lifeOfBeesGame.getCurrentDate()), lifeOfBeesGame);
        double totalCost = numberOfHives * 500;
        if (lifeOfBeesGame.getMoneyInTheBank() < totalCost) {
            return ResponseEntity.badRequest().body("Insufficient funds to buy hives.");
        }
        lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() - totalCost);
        lifeOfBeesRepository.save(lifeOfBeesGame);
        return ResponseEntity.ok("Hives bought successfully.");
    }

    @GetMapping("/JohnDoeGames")
    public List<HomePageGameResponse> getJohnDoeGames() {
        List<LifeOfBees> userGames = lifeOfBeesService.getGamesForJohnDoe();
        if (userGames.isEmpty()) {
            System.out.println("No games for JohnDoe.");
        } else {
            System.out.println("JohnDoe's games: " + userGames);
        }
        return userGames.stream()
                .map(game -> {
                    System.out.println("Total Honey Harvested (before): " + game.getTotalKgOfHoneyHarvested());
                    System.out.println("Money in the Bank (before): " + game.getMoneyInTheBank());
                    return new HomePageGameResponse(
                            game.getGameName(),
                            game.getLocation(),
                            game.getApiary().getHives().size(),
                            game.getMoneyInTheBank(),
                            game.getTotalKgOfHoneyHarvested(),
                            game.getGameId()
                    );
                })
                .collect(Collectors.toList());

    }

    @GetMapping("/gamesForUser")
    public List<HomePageGameResponse> getGamesForUserByType(
            @RequestParam String userId,
            @RequestParam(required = false) String gameType) {
        List<LifeOfBees> userGames = lifeOfBeesService.getGamesForUserByType(userId, gameType);
        if (gameType != null) {
            userGames = userGames.stream()
                    .filter(game -> game.getGameType().equalsIgnoreCase(gameType))
                    .toList();
        }
        if (userGames.isEmpty()) {
            System.out.println("No games found for for userId: " + userId
                    + (gameType != null ? " game type: " + gameType : "."));
        } else {
            System.out.println("Games found for userId: " + userId
                    + (gameType != null ? " game type: " + gameType : "")
                    + ": " + userGames);
        }
        return userGames.stream()
                .map(game -> new HomePageGameResponse(
                        game.getGameName(),
                        game.getLocation(),
                        game.getApiary().getHives().size(),
                        game.getMoneyInTheBank(),
                        game.getTotalKgOfHoneyHarvested(),
                        game.getGameId()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/HiveHistory/{gameId}")
    public List<HiveHistory> getHiveHistory(@PathVariable String gameId,
                                            @RequestParam("hiveId") Integer hiveId) {
        GameHistory gameHistory = gameHistoryService.findGameBygameId(gameId);

        List<HiveHistory> hiveHistories = new ArrayList<>();
        for (LifeOfBees game : gameHistory.getGamesHistory()) {
            HiveHistory hiveHistory = new HiveHistory();
            hiveHistory.setCurrentDate(game.getCurrentDate());
            hiveHistory.setWeatherData(game.getWeatherData());
            hiveHistory.setMoneyInTheBank(game.getMoneyInTheBank());
            hiveHistory.setHive(game.getApiary().getHiveById(hiveId));
            hiveHistories.add(hiveHistory);
        }
        return hiveHistories;
    }

    @GetMapping("/apiaryHistory/{gameId}")
    public ResponseEntity<List<ApiaryHistory>> getApiaryHistory(@PathVariable String gameId) throws JsonProcessingException {
        GameHistory gameHistory = gameHistoryService.findGameBygameId(gameId);
        List<ApiaryHistory> apiaryHistories = new ArrayList<>();
        for (LifeOfBees game : gameHistory.getGamesHistory()) {
            Apiary apiary = game.getApiary();
            ApiaryHistory apiaryHistory = new ApiaryHistory();
            apiaryHistory.setCurrentDate(game.getCurrentDate());
            apiaryHistory.setWeatherData(game.getWeatherData());
            apiaryHistory.setMoneyInTheBank(game.getMoneyInTheBank());
            apiaryHistory.setTotalKgOfHoneyHarvested(game.getTotalKgOfHoneyHarvested());
            apiaryHistory.setActionsOfTheWeek(game.getActionsOfTheWeek());
            apiaryHistory.setHive(apiary.getHives());
            apiaryHistories.add(apiaryHistory);
        }
        return ResponseEntity.ok(apiaryHistories);
    }

}


