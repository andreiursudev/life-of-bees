package com.marianbastiurea.lifeofbees.history;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.weather.WeatherData;

import java.time.LocalDate;
import java.util.List;


public class ApiaryHistory {

    private LocalDate currentDate;
    private WeatherData weatherData;
    private double moneyInTheBank;
    private double totalKgOfHoneyHarvested;
    private List<String> actionsOfTheWeek;
    private List<Hive> hive;

    public ApiaryHistory(LocalDate currentDate, WeatherData weatherData, double moneyInTheBank,
                         double totalKgOfHoneyHarvested, List<String> actionsOfTheWeek, List<Hive> hive) {
        this.currentDate = currentDate;
        this.weatherData = weatherData;
        this.moneyInTheBank = moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
        this.actionsOfTheWeek = actionsOfTheWeek;
        this.hive = hive;
    }

    public List<Hive> getHive() {
        return hive;
    }

    public void setHive(List<Hive> hive) {
        this.hive = hive;
    }

    public ApiaryHistory() {
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
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

    public List<String> getActionsOfTheWeek() {
        return actionsOfTheWeek;
    }

    public void setActionsOfTheWeek(List<String> actionsOfTheWeek) {
        this.actionsOfTheWeek = actionsOfTheWeek;
    }

    public double getTotalKgOfHoneyHarvested() {
        return totalKgOfHoneyHarvested;
    }

    public void setTotalKgOfHoneyHarvested(double totalKgOfHoneyHarvested) {
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

    @Override
    public String toString() {
        return "ApiaryHistory{" +
                "currentDate=" + currentDate +
                ", weatherData=" + weatherData +
                ", moneyInTheBank=" + moneyInTheBank +
                ", totalKgOfHoneyHarvested=" + totalKgOfHoneyHarvested +
                ", actionOfTheWeek=" + actionsOfTheWeek +
                ", hive=" + hive +
                '}';
    }
}