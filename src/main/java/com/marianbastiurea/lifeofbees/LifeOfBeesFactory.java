package com.marianbastiurea.lifeofbees;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;




public class LifeOfBeesFactory {

    public static LifeOfBees createLifeOfBeesGame(
            String gameName,
            String location,
            String startingDate,
            int numberOfStartingHives,
            String userId,
            boolean isPublic,
            Map<String, WeatherData> allWeatherData
            )


            {

        LocalDate date = LocalDate.parse(startingDate);
        List<ActionOfTheWeek> actionOfTheWeek = new ArrayList<>();
        double moneyInTheBank = 3000.0;
        double totalKgOfHoney = 0;
        Apiary apiary = new Apiary(new ArrayList<>());
        List<Hive> newHives = apiary.createHive(numberOfStartingHives, date);
        apiary.getHives().addAll(newHives);
        WeatherData weatherData = allWeatherData.get(date.toString());
        return new LifeOfBees(gameName,apiary,actionOfTheWeek, location, date, weatherData, moneyInTheBank, totalKgOfHoney);
    }
}