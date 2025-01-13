package com.marianbastiurea.lifeofbees.view;

import com.marianbastiurea.lifeofbees.action.ActionsOfTheWeek;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import java.util.ArrayList;
import java.util.List;

public class GameResponse {

    private String id;
    private List<HivesView> hives = new ArrayList<>();
    private double temperature;
    private double windSpeed;
    private double precipitation;
    private double moneyInTheBank;
    private BeeTime currentDate;
    private double totalKgOfHoneyHarvested;
    private ActionsOfTheWeek actionsOfTheWeek;
    private Integer removedHiveId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BeeTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(BeeTime currentDate) {
        this.currentDate = currentDate;
    }

    public List<HivesView> getHives() {
        return hives;
    }

    public void setHives(List<HivesView> hives) {
        this.hives = hives;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getMoneyInTheBank() {
        return moneyInTheBank;
    }

    public void setMoneyInTheBank(double moneyInTheBank) {
        this.moneyInTheBank = moneyInTheBank;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getTotalKgOfHoneyHarvested() {
        return totalKgOfHoneyHarvested;
    }

    public void setTotalKgOfHoneyHarvested(double totalKgOfHoneyHarvested) {
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

    public void setWeatherData(WeatherData weatherData) {
        if (weatherData != null) {
            this.temperature = weatherData.getTemperature();
            this.precipitation = weatherData.getPrecipitation();
            this.windSpeed = weatherData.getWindSpeed();
        }
    }

    public ActionsOfTheWeek getActionsOfTheWeek() {
        return actionsOfTheWeek;
    }

    public void setActionsOfTheWeek(ActionsOfTheWeek actionsOfTheWeek) {
        this.actionsOfTheWeek = actionsOfTheWeek;
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "id='" + id + '\'' +
                ", hives=" + hives +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", precipitation=" + precipitation +
                ", moneyInTheBank=" + moneyInTheBank +
                ", currentDate=" + currentDate +
                ", totalKgOfHoneyHarvested=" + totalKgOfHoneyHarvested +
                ", actionsOfTheWeek=" + actionsOfTheWeek +
                '}';
    }

    public void setRemovedHiveId(Integer removedHiveId) {
        this.removedHiveId = removedHiveId;
    }

    public Integer getRemovedHiveId() {
        return removedHiveId;
    }
}

