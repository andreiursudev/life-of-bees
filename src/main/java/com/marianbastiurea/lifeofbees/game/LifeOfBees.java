package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.action.ActionsOfTheWeek;
import com.marianbastiurea.lifeofbees.bees.*;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Document(collection = "games")
public class LifeOfBees {
    @Id
    private String gameId;
    private String userId;
    private Apiary apiary;
    private ActionsOfTheWeek actionsOfTheWeek;
    private String gameName;
    private String location;
    private boolean yearIsChanged;

    //TODO Replace currentDate with BeeTime
    private LocalDate currentDate;
    private WeatherData weatherData;
    private double moneyInTheBank;
    private double totalKgOfHoneyHarvested;
    private String gameType;
    private Map<String, WeatherData> allWeatherData = new HashMap<>();



    public LifeOfBees(String gameId, String gameType, String userId, Apiary apiary,
                      String gameName, String location, LocalDate currentDate,
                      WeatherData weatherData, double moneyInTheBank, double totalKgOfHoneyHarvested,
                      ActionsOfTheWeek actionsOfTheWeek, boolean yearIsChanged) {
        this.gameId = gameId;
        this.userId = userId;
        this.gameType = gameType;
        this.apiary = apiary;
        this.gameName = gameName;
        this.location = location;
        this.currentDate = currentDate;
        this.moneyInTheBank = moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
        this.actionsOfTheWeek = actionsOfTheWeek;
        this.weatherData = weatherData;
        this.yearIsChanged = yearIsChanged;
    }

    public LifeOfBees(String gameName, String userId, String gameType, Apiary apiary,
                      String location, LocalDate currentDate, WeatherData weatherData,
                      double moneyInTheBank, double totalKgOfHoneyHarvested, boolean yearIsChanged) {
        this.apiary = apiary;
        this.gameName = gameName;
        this.location = location;
        this.currentDate = currentDate;
        this.weatherData = weatherData;
        this.moneyInTheBank = moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
        this.userId = userId;
        this.gameType = gameType;
        this.yearIsChanged = yearIsChanged;
    }


    public LifeOfBees(LocalDate currentDate, WeatherData weatherData, Apiary apiary, double moneyInTheBank, double totalKgOfHoneyHarvested) {
        this.currentDate = currentDate;
        this.weatherData = weatherData;
        this.apiary = apiary;
        this.moneyInTheBank = moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

    @Override
    public String toString() {
        return "LifeOfBees{" +
                "gameId='" + gameId + '\'' +
                ", userId='" + userId + '\'' +
                ", apiary=" + apiary +
                ", actionsOfTheWeek=" + actionsOfTheWeek +
                ", gameName='" + gameName + '\'' +
                ", location='" + location + '\'' +
                ", currentDate=" + currentDate +
                ", weatherData=" + weatherData +
                ", moneyInTheBank=" + moneyInTheBank +
                ", totalKgOfHoneyHarvested=" + totalKgOfHoneyHarvested +
                ", gameType='" + gameType + '\'' +
                '}';
    }

    public LifeOfBees() {
    }

    public LifeOfBees iterateOneWeek(LifeOfBees lifeOfBeesGame, LifeOfBeesService lifeOfBeesService, Object data,List<WeatherData> weatherDataNextWeek) {
        ActionsOfTheWeek actionsOfTheWeek = new ActionsOfTheWeek();
        actionsOfTheWeek.executeActions(lifeOfBeesGame, data);
        System.out.println("Data curentă în joc: " + lifeOfBeesGame.getCurrentDate());
        Map<String, WeatherData> allWeatherData = lifeOfBeesGame.getAllWeatherData();
        WeatherData dailyWeather = null;
        System.out.println("Weather data primit: " + allWeatherData );
        for (int dailyIterator = 0; dailyIterator < 7; dailyIterator++) {
            dailyWeather = weatherDataNextWeek.stream()
                    .filter(weather -> weather.getDate().isEqual(currentDate))
                    .findFirst()
                    .orElse(null);

            if (dailyWeather == null) {
                throw new RuntimeException("Weather data not found for " + currentDate);
            }
            for (Hive hive : apiary.getHives()) {
                Queen queen = hive.getQueen();
                hive.changeQueen(currentDate);
                double weatherIndex = dailyWeather.weatherIndex(dailyWeather);
                int numberOfEggs = queen.ageOneDay(currentDate, weatherIndex);
                hive.ageOneDay(numberOfEggs);
                hive.fillUpExistingHoneyFramesFromHive(currentDate);
                hive.getBeesBatches().removeFirst();
                List<HoneyBatch> harvestedHoneyBatches = Honey.harvestHoney(hive, currentDate);
                hive.addHoneyBatches(harvestedHoneyBatches);
            }

            apiary.honeyHarvestedByHoneyType();
            System.out.println(apiary.getTotalHarvestedHoney());
            lifeOfBeesGame.setTotalKgOfHoneyHarvested(apiary.getTotalKgHoneyHarvested());

            if (currentDate.isEqual(LocalDate.of(currentDate.getYear(), 9, 30))) {
                currentDate = LocalDate.of(currentDate.getYear() + 1, 3, 1);
                lifeOfBeesGame.setCurrentDate(currentDate);
                lifeOfBeesGame.setYearIsChanged(true);
                yearIsChanged = true;
                break;
            }
            currentDate = currentDate.plusDays(1);
        }
        actionsOfTheWeek.addAllActions(lifeOfBeesGame);
        lifeOfBeesGame.setActionsOfTheWeek(actionsOfTheWeek);
        lifeOfBeesGame.setCurrentDate(currentDate);
        return new LifeOfBees(gameId, gameType, userId, apiary, gameName, location, currentDate, dailyWeather, moneyInTheBank, totalKgOfHoneyHarvested, actionsOfTheWeek, yearIsChanged);
    }


    public Apiary getApiary() {
        return apiary;
    }

    public void setApiary(Apiary apiary) {
        this.apiary = apiary;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public double getMoneyInTheBank() {
        return moneyInTheBank;
    }

    public void setMoneyInTheBank(double moneyInTheBank) {
        this.moneyInTheBank = moneyInTheBank;
    }

    public String getGameName() {
        return gameName;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public double getTotalKgOfHoneyHarvested() {
        return totalKgOfHoneyHarvested;
    }

    public void setTotalKgOfHoneyHarvested(double totalKgOfHoneyHarvested) {
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public ActionsOfTheWeek getActionsOfTheWeek() {
        return actionsOfTheWeek;
    }

    public void setActionsOfTheWeek(ActionsOfTheWeek actionsOfTheWeek) {
        this.actionsOfTheWeek = actionsOfTheWeek;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public boolean isYearIsChanged() {
        return yearIsChanged;
    }

    public void setYearIsChanged(boolean yearIsChanged) {
        this.yearIsChanged = yearIsChanged;
    }

    public Map<String, WeatherData> getAllWeatherData() {
        return allWeatherData;
    }

    public void setAllWeatherData(Map<String, WeatherData> allWeatherData) {
        this.allWeatherData = allWeatherData;
    }
}