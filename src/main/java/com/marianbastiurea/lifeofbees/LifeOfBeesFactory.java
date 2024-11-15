package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;


public class LifeOfBeesFactory {
    public static LifeOfBees createLifeOfBeesGame(Integer gameId, String gameName, String location, String startingDate, int numberOfStartingHives, Map<String, WeatherData> allWeatherData) {
        // Convertește startingDate în LocalDate
        LocalDate date = LocalDate.parse(startingDate);

        // Creează o listă de acțiuni
        List<ActionOfTheWeek> actionOfTheWeek = new ArrayList<>();

        // Inițializează variabilele jocului
        double moneyInTheBank = 3000.0;
        double totalKgOfHoney = 0;

        // Creează obiectul Apiary
        Apiary apiary = new Apiary(new ArrayList<>());

        // Creează stupii
        List<Hive> newHives = apiary.createHive(numberOfStartingHives, date);
        apiary.getHives().addAll(newHives);

        System.out.println("acesta e allWeatherData primit in LifeOfBeesFactory:"+allWeatherData);

        // Creează obiectul Weather pentru datele meteo completeÒ
        Weather gameWeatherData = new Weather(allWeatherData);
        System.out.println("acestea sunt datele meteo pentru tot jocul gameWeatherData"+gameWeatherData);

        // Obține datele meteo pentru data respectivă
        WeatherData weatherData = gameWeatherData.getDailyWeatherDataForDate(date, allWeatherData);



        // Creează și returnează obiectul LifeOfBees
        return new LifeOfBees(apiary, gameId, gameName, location, date, weatherData, moneyInTheBank, totalKgOfHoney, actionOfTheWeek,gameWeatherData);
    }

}