package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;


public class LifeOfBeesFactory {
    public static LifeOfBees createLifeOfBeesGame(Integer gameId, String gameName, String location, String startingDate, int numberOfStartingHives, Map<String, WeatherData> allWeatherData) {
        LocalDate date = LocalDate.parse(startingDate);
        List<ActionOfTheWeek> actionOfTheWeek = new ArrayList<>();
        double moneyInTheBank = 3000.0;
        double totalKgOfHoney = 0;
        Apiary apiary = new Apiary(new ArrayList<>());
        List<Hive> newHives = apiary.createHive(numberOfStartingHives, date);
        apiary.getHives().addAll(newHives);
        Weather gameWeatherData = new Weather(allWeatherData);
        WeatherData weatherData = gameWeatherData.getDailyWeatherDataForDate(date, allWeatherData);
        return new LifeOfBees(apiary, gameId, gameName, location, date, weatherData, moneyInTheBank, totalKgOfHoney, actionOfTheWeek,gameWeatherData);
    }

}