package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class LifeOfBeesFactory {

    public static LifeOfBees createLifeOfBeesGame(Integer gameId, String gameName, String location, String startingDate, int numberOfStartingHives) {
        LocalDate date = LocalDate.parse(startingDate);
        int day = date.getDayOfMonth();
        int monthValue = date.getMonthValue();
        HarvestingMonths month =HarvestingMonths.values()[monthValue - 3];
        List<ActionOfTheWeek> actionOfTheWeek = new ArrayList<>();
        Weather weather = new Weather();
        Weather todayWeather = weather.whetherToday(month, day);
        double speedWind = todayWeather.getSpeedWind();
        double temperature = todayWeather.getTemperature();
        double precipitation = todayWeather.getPrecipitation();
        double moneyInTheBank = 3000.0;
        double totalKgOfHoney = 0;

        Apiary apiary = new Apiary(new ArrayList<>());
        List<Hive> newHives = apiary.createHive(numberOfStartingHives, date);
        apiary.getHives().addAll(newHives);
        return new LifeOfBees(apiary, gameId, gameName, location, date, speedWind, temperature, precipitation, moneyInTheBank, totalKgOfHoney, actionOfTheWeek);
    }
}