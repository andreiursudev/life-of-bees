package com.marianbastiurea.lifeofbees.history;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.weather.WeatherData;

import java.time.LocalDate;

public class HiveHistory {
    private LocalDate currentDate;
    private WeatherData weatherData;
    private double moneyInTheBank;
    private Hive hive;


    public HiveHistory() {
    }

    public HiveHistory( LocalDate currentDate, WeatherData weatherData, double moneyInTheBank, Hive hive) {
        this.currentDate = currentDate;
        this.weatherData = weatherData;
        this.moneyInTheBank = moneyInTheBank;
        this.hive = hive;
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

    public Hive getHive() {
        return hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }

    @Override
    public String toString() {
        return "HiveHistory{" +
                ", currentDate=" + currentDate +
                ", weatherData=" + weatherData +
                ", moneyInTheBank=" + moneyInTheBank +
                ", hive=" + hive +
                '}';
    }
}
