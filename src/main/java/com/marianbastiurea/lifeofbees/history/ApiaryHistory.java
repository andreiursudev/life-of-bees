package com.marianbastiurea.lifeofbees.history;

import com.marianbastiurea.lifeofbees.action.ActionsOfTheWeek;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import com.marianbastiurea.lifeofbees.weather.WeatherData;

import java.util.List;


public class ApiaryHistory {

    private BeeTime currentDate;
    private WeatherData weatherData;
    private double moneyInTheBank;
    private double totalKgOfHoneyHarvested;
    private ActionsOfTheWeek actionsOfTheWeek;
    private Hives hives;

    public ApiaryHistory(BeeTime currentDate, WeatherData weatherData, double moneyInTheBank,
                         double totalKgOfHoneyHarvested, ActionsOfTheWeek actionsOfTheWeek, Hives hives) {
        this.currentDate = currentDate;
        this.weatherData = weatherData;
        this.moneyInTheBank = moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
        this.actionsOfTheWeek = actionsOfTheWeek;
        this.hives = hives;
    }

    public ApiaryHistory() {
    }

    public BeeTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(BeeTime currentDate) {
        this.currentDate = currentDate;
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

    public ActionsOfTheWeek getActionsOfTheWeek() {
        return actionsOfTheWeek;
    }

    public void setActionsOfTheWeek(ActionsOfTheWeek actionsOfTheWeek) {
        this.actionsOfTheWeek = actionsOfTheWeek;
    }

    public double getTotalKgOfHoneyHarvested() {
        return totalKgOfHoneyHarvested;
    }

    public void setTotalKgOfHoneyHarvested(double totalKgOfHoneyHarvested) {
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

    public Hives getHives() {
        return hives;
    }

    public void setHives(Hives hives) {
        this.hives = hives;
    }

    @Override
    public String toString() {
        return "ApiaryHistory{" +
                "currentDate=" + currentDate +
                ", weatherData=" + weatherData +
                ", moneyInTheBank=" + moneyInTheBank +
                ", totalKgOfHoneyHarvested=" + totalKgOfHoneyHarvested +
                ", actionOfTheWeek=" + actionsOfTheWeek +
                ", hives=" + hives +
                '}';
    }
}