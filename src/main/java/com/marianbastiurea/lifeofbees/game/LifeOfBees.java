package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.action.ActionType;
import com.marianbastiurea.lifeofbees.action.ActionsOfTheWeek;
import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Document(collection = "games")
public class LifeOfBees {
    private final String gameName;
    private final String location;
    @Id
    private String gameId;
    private String userId;
    private Apiary apiary;
    private ActionsOfTheWeek actionsOfTheWeek;
    private Integer removedHiveId;
    private BeeTime currentDate;
    private WeatherData weatherData;
    private double moneyInTheBank;
    private double totalKgOfHoneyHarvested;
    private String gameType;

    public LifeOfBees(String gameName, String userId, String gameType, Apiary apiary,
                      String location, BeeTime currentDate, WeatherData weatherData,
                      double moneyInTheBank, double totalKgOfHoneyHarvested, ActionsOfTheWeek actionsOfTheWeek) {
        this.apiary = apiary;
        this.gameName = gameName;
        this.location = location;
        this.currentDate = currentDate;
        this.weatherData = weatherData;
        this.moneyInTheBank = moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
        this.userId = userId;
        this.gameType = gameType;
        this.actionsOfTheWeek = actionsOfTheWeek;
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
                ", removedHiveId=" + removedHiveId +
                ", currentDate=" + currentDate +
                ", weatherData=" + weatherData +
                ", moneyInTheBank=" + moneyInTheBank +
                ", totalKgOfHoneyHarvested=" + totalKgOfHoneyHarvested +
                ", gameType='" + gameType + '\'' +
                '}';
    }

    public void iterateOneWeek(Map<ActionType, Object> actions, List<WeatherData> weatherDataNextWeek) {
        ActionsOfTheWeek actionsOfTheWeek = new ActionsOfTheWeek();
        actionsOfTheWeek.executeActions(this, actions);
        for (int dailyIterator = 0; dailyIterator < 7; dailyIterator++) {
            double weatherIndex = weatherDataNextWeek.get(dailyIterator).weatherIndex();
            for (Hive hive : apiary.getHives()) {
                hive.iterateOneDay(currentDate, weatherIndex);
            }
            apiary.honeyHarvestedByHoneyType();
            this.setTotalKgOfHoneyHarvested(apiary.getTotalKgHoneyHarvested());
            BeeTime targetDate = new BeeTime(currentDate.getYear(), 9, 30);
            if (currentDate.isEqual(targetDate)) {
                currentDate.updateDate(currentDate.getYear() + 1, 3, 1);
                Integer removedHiveId = apiary.hibernate();
                this.setRemovedHiveId(removedHiveId);
                break;
            } else {
                this.setRemovedHiveId(null);
            }
            currentDate.addDays(1);
        }
        actionsOfTheWeek.createActions(this);
        this.setActionsOfTheWeek(actionsOfTheWeek);
        System.out.println("acestea sunt actions:" + actionsOfTheWeek);
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

    public BeeTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(BeeTime currentDate) {
        this.currentDate = currentDate;
    }

    public double getTotalKgOfHoneyHarvested() {
        return totalKgOfHoneyHarvested;
    }

    public void setTotalKgOfHoneyHarvested(double totalKgOfHoneyHarvested) {
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
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

    public Integer getRemovedHiveId() {
        return removedHiveId;
    }

    public void setRemovedHiveId(Integer removedHiveId) {
        this.removedHiveId = removedHiveId;
    }
}