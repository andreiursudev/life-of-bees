package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import com.marianbastiurea.lifeofbees.weather.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class LifeOfBeesFactory {

    public static LifeOfBees createLifeOfBeesGame(
            String gameName,
            String location,
            String startingDate,
            int numberOfStartingHives,
            String userId,
            String gameType,
            WeatherData weatherData
    ) {
        BeeTime date = new BeeTime(startingDate);
        double moneyInTheBank = 3000.0;
        double totalKgOfHoney = 0;
        Hives hives = Hives.createHives(numberOfStartingHives, date);
        Apiary apiary = new Apiary(hives);
        return new LifeOfBees(gameName, userId, gameType, apiary, location,
                weatherData, moneyInTheBank, totalKgOfHoney, null);
    }
}