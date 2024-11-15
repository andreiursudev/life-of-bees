package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;
import java.util.*;
import java.util.List;


public class LifeOfBees {
    private Apiary apiary;
    private List<ActionOfTheWeek> actionOfTheWeek;
    private Integer gameId;
    private String gameName;
    private String location;
    private LocalDate currentDate;
    private WeatherData weatherData;
    private Weather gameWeatherData;

    //todo: group speedWind, temperature and precipitation in a class separate class
//    private double speedWind;// in km/h
//    private double temperature;// in Celsius Degree
//    private double precipitation;
    private double moneyInTheBank;
    private double totalKgOfHoneyHarvested;


    public LifeOfBees(Apiary apiary, Integer gameId,
                      String gameName, String location, LocalDate currentDate,
                      WeatherData weatherData, double moneyInTheBank, double totalKgOfHoneyHarvested,
                      List<ActionOfTheWeek> actionOfTheWeek, Weather gameWeatherData) {
        this.apiary = apiary;
        this.gameId = gameId;
        this.gameName = gameName;
        this.location = location;
        this.currentDate = currentDate;
        this.moneyInTheBank = moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
        this.actionOfTheWeek = actionOfTheWeek;
        this.weatherData = weatherData;
        this.gameWeatherData=gameWeatherData;
    }


    public LifeOfBees(Weather gameWeatherData) {
        this.gameWeatherData = gameWeatherData;
    }

    public Weather getGameWeatherData() {
        return gameWeatherData;
    }

    public void setGameWeatherData(Weather gameWeatherData) {
        this.gameWeatherData = gameWeatherData;
    }

    @Override
    public String toString() {
        return "LifeOfBees{" +
                "apiary=" + apiary +
                ", actionOfTheWeek=" + actionOfTheWeek +
                ", gameId=" + gameId +
                ", gameName='" + gameName + '\'' +
                ", location='" + location + '\'' +
                ", currentDate=" + currentDate +
                ", weatherData=" + weatherData +
                ", moneyInTheBank=" + moneyInTheBank +
                ", totalKgOfHoneyHarvested=" + totalKgOfHoneyHarvested +
                '}';
    }

    public LifeOfBees iterateOneWeek(LifeOfBees lifeOfBeesGame) {
        LocalDate date = lifeOfBeesGame.getCurrentDate();

        // Inițializează obiectul Weather cu toate datele meteo stocate în joc
        Weather weather = lifeOfBeesGame.getGameWeatherData();
        System.out.println("acestea sunt datele meteo in metoda iterateOneWeek: "+weather);

        // Obține datele meteo zilnice pentru data curentă
        WeatherData dailyWeather = weather.getDailyWeatherDataForDate(date, weather.getAllWeatherData());
        System.out.println("Acestea sunt datele meteo pentru azi: " + dailyWeather);



        List<ActionOfTheWeek> actionsOfTheWeek = new ArrayList<>();


        for (int dailyIterator = 0; dailyIterator < 7; dailyIterator++) {
            List<Hive> hives = apiary.getHives();
            ArrayList<Hive> oldHives = new ArrayList<>(hives);
            for (Hive hive : oldHives) {
                Honey honey = new Honey();
                HarvestingMonths month = honey.getHarvestingMonth(date);
                Queen queen = hive.getQueen();
                double numberRandom = Math.random();
                if ((numberRandom < 0.5 && month.equals(HarvestingMonths.MAY) && date.getDayOfMonth() > 1 && date.getDayOfMonth() < 20) || queen.getAgeOfQueen() == 5) {
                    hive.changeQueen();
                }
                double whetherIndex = dailyWeather.weatherIndex(dailyWeather);
                int numberOfEggs = queen.makeEggs(honey, whetherIndex);
                hive.fillUpEggsFrame(numberOfEggs);
                hive.checkIfCanAddNewEggsFrameInHive(actionsOfTheWeek);
                hive.checkIfHiveCouldBeSplit(month, date.getDayOfMonth(), actionsOfTheWeek, lifeOfBeesGame);
                hive.checkAndAddEggsToBees(hive.getEggFrames());
                hive.fillUpExistingHoneyFrameFromHive(lifeOfBeesGame);
                hive.getBeesBatches().removeLast();
                List<HoneyBatch> harvestedHoneyBatches = honey.harvestHoney(hive, month, date.getDayOfMonth());
                hive.addHoneyBatches(harvestedHoneyBatches, actionsOfTheWeek);
                hive.checkIfCanAddANewHoneyFrameInHive(actionsOfTheWeek);
                hive.checkIfCanMoveAnEggsFrame(actionsOfTheWeek, lifeOfBeesGame);
                apiary.checkInsectControl(month, date.getDayOfMonth(), actionsOfTheWeek);
                apiary.checkFeedBees(month, date.getDayOfMonth(), actionsOfTheWeek);
            }
            apiary.honeyHarvestedByHoneyType();
            System.out.println(apiary.getTotalHarvestedHoney());
            lifeOfBeesGame.setTotalKgOfHoneyHarvested(apiary.getTotalKgHoneyHarvested());

            if (date.isEqual(LocalDate.of(date.getYear(), 9, 30))) {
                apiary.hibernate(lifeOfBeesGame, actionsOfTheWeek);
                date = LocalDate.of(date.getYear() + 1, 3, 1);
                lifeOfBeesGame.setCurrentDate(date);
                break;
            }
            lifeOfBeesGame.setActionOfTheWeek(actionsOfTheWeek);
            System.out.println("Action of the day " + date.getDayOfMonth() + " is " + lifeOfBeesGame.getActionOfTheWeek());
            date = date.plusDays(1);
        }
        lifeOfBeesGame.setCurrentDate(date);
        return new LifeOfBees(apiary, gameId, gameName, location, date, dailyWeather, moneyInTheBank, totalKgOfHoneyHarvested, actionOfTheWeek, gameWeatherData);
    }

    public Integer getGameId() {
        return gameId;
    }


    public Apiary getApiary() {
        return apiary;
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

    public String getGameName() {
        return gameName;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public double getTotalKgOfHoneyHarvested() {
        return totalKgOfHoneyHarvested;
    }

    public void setTotalKgOfHoneyHarvested(double totalKgOfHoneyHarvested) {
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public List<ActionOfTheWeek> getActionOfTheWeek() {
        return actionOfTheWeek;
    }

    public void setActionOfTheWeek(List<ActionOfTheWeek> actionOfTheWeek) {
        this.actionOfTheWeek = actionOfTheWeek;
    }
}