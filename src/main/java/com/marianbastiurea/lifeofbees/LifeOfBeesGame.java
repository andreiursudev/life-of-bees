package com.marianbastiurea.lifeofbees;

import com.marianbastiurea.lifeofbees.eggframe.EggFrame;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.marianbastiurea.lifeofbees.Honey.getHarvestingMonth;

public class LifeOfBeesGame {

    private Apiary apiary;// apiary is the place where it will be stored all hives
    private int hiveIdCounter = 1;
    private String name;
    private String location;
    private String startingDate;
    private int numberOfStartingHives;
    private double speedWind;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;


    public LifeOfBeesGame(String name, String location, String startingDate, int numberOfStartingHives) {
        this.name = name;
        this.location = location;
        this.startingDate = startingDate;
        this.numberOfStartingHives = numberOfStartingHives;
    }


    public List<GameResponse> createApiary(int numberOfStartingHives) {
        List<GameResponse> gameResponses = new ArrayList<>();
        LocalDate date = LocalDate.parse(startingDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        int day = date.getDayOfMonth();
        HarvestingMonths month = getHarvestingMonth(date);

        Whether whether = new Whether();
        Whether todayWeather = whether.whetherToday(month, day);
        double speedWind = todayWeather.getSpeedWind();
        double temperature = todayWeather.getTemperature();
        double precipitation = todayWeather.getPrecipitation();

        List<Hive> hives = new ArrayList<>();
        Honey honey = new Honey();
        String honeyType = honey.honeyTypes(month, day);
        Random random = new Random();
        Apiary apiary = new Apiary(hives, new ArrayList<>());
        for (int i = 1; i < numberOfStartingHives + 1; i++) {
            int ageOfQueen = random.nextInt(1, 6);
            List<EggFrame> eggFrames = new ArrayList<>();
            for (int j = 0; j < random.nextInt(5, 6); j++) {
                eggFrames.add(new EggFrame());
            }
            List<HoneyFrame> honeyFrames = new ArrayList<>();
            for (int k = 0; k < random.nextInt(4, 5); k++) {
                honeyFrames.add(new HoneyFrame(random.nextDouble(2.5, 3), "WildFlower"));
            }
            int numberOfBees = random.nextInt(2000, 2500) * (honeyFrames.size() + eggFrames.size());
            Hive hive = new Hive(apiary,
                    i,
                    false,
                    false,
                    false,
                    eggFrames,
                    honeyFrames,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new Honey(honeyType),
                    new Queen(ageOfQueen),
                    numberOfBees);
            hives.add(hive);

            GameResponse gameResponse = new GameResponse();
            gameResponse.setHiveId(i);
            gameResponse.setAgeOfQueen(ageOfQueen);
            gameResponse.setNumberOfBees(numberOfBees);
            gameResponse.setEggsFrameSize(eggFrames.size());
            gameResponse.setHoneyFrameSize(honeyFrames.size());
            gameResponse.setSpeedWind(speedWind);
            gameResponse.setTemperature(temperature);
            gameResponse.setPrecipitation(precipitation);
            gameResponse.setCurrentDate(startingDate);
            gameResponses.add(gameResponse);
        }
        return gameResponses;
    }
    public String serializeGameResponses(List<GameResponse> gameResponses) {
        Gson gson = new Gson();
        return gson.toJson(gameResponses);
    }


    public static void main(String[] args) {
        LifeOfBeesGame game = new LifeOfBeesGame("Bee Game", "Location", "2024-06-01", 5);
        List<GameResponse> gameResponses = game.createApiary(5);


        String jsonResponse = game.serializeGameResponses(gameResponses);
        System.out.println(gameResponses);
        System.out.println(jsonResponse);



    }
}
