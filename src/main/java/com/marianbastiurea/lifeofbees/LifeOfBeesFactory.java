package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static com.marianbastiurea.lifeofbees.Honey.getHarvestingMonth;

public class LifeOfBeesFactory {

    public static LifeOfBees createLifeOfBeesGame(Integer gameId, String gameName, String location, String startingDate, int numberOfStartingHives) {
        LocalDate date = LocalDate.parse(startingDate);
        int day = date.getDayOfMonth();
        HarvestingMonths month = getHarvestingMonth(date);
        List<ActionOfTheWeek> actionOfTheWeek = new ArrayList<>();

        Weather weather = new Weather();
        Weather todayWeather = weather.whetherToday(month, day);
        double speedWind = todayWeather.getSpeedWind();
        double temperature = todayWeather.getTemperature();
        double precipitation = todayWeather.getPrecipitation();
        double moneyInTheBank = 3000.0;
        double totalKgOfHoney = 0;

        Apiary apiary = new Apiary(new ArrayList<>(), new ArrayList<>());
        List<Hive> newHives = apiary.createHive(numberOfStartingHives, date);
        apiary.getHives().addAll(newHives);
        return new LifeOfBees(apiary, gameId, gameName, location, date, speedWind, temperature, precipitation, moneyInTheBank, totalKgOfHoney, actionOfTheWeek);
    }
}