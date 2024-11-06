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

    //todo: group speedWind, temperature and precipitation in a class separate class
    private double speedWind;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;
    private double moneyInTheBank;
    private double totalKgOfHoneyHarvested;


    public LifeOfBees(Apiary apiary, Integer gameId,
                      String gameName, String location, LocalDate currentDate,
                      double speedWind, double temperature, double precipitation, double moneyInTheBank, double totalKgOfHoneyHarvested, List<ActionOfTheWeek> actionOfTheWeek) {
        this.apiary = apiary;
        this.gameId = gameId;
        this.gameName = gameName;
        this.location = location;
        this.currentDate = currentDate;
        this.speedWind = speedWind;
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.moneyInTheBank = moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
        this.actionOfTheWeek = actionOfTheWeek;
    }

    @Override
    public String toString() {
        return "LifeOfBees{" +
                "apiary=" + apiary +
                ", action=" + actionOfTheWeek +
                ", gameId=" + gameId +
                ", gameName='" + gameName + '\'' +
                ", location='" + location + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", speedWind=" + speedWind +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                ", actionOfTheWeek='" + actionOfTheWeek + '\'' +
                ", moneyInTheBank=" + moneyInTheBank +
                ", totalKgOfHoneyHarvested=" + totalKgOfHoneyHarvested +
                '}';
    }


    public LifeOfBees iterateOneWeek(LifeOfBees lifeOfBeesGame) {
        LocalDate date = lifeOfBeesGame.getCurrentDate();
        Weather weather = new Weather();
        List<ActionOfTheWeek> actionsOfTheWeek = new ArrayList<>();
        Weather todayWeather = null;

        for (int dailyIterator = 0; dailyIterator < 7; dailyIterator++) {
            List<Hive> hives = apiary.getHives();
            ArrayList<Hive> oldHives = new ArrayList<>(hives);
            for (Hive hive : oldHives) {
                Honey honey = hive.getHoney();
                HarvestingMonths month = honey.getHarvestingMonth(date);
                todayWeather = weather.whetherToday(month, date.getDayOfMonth());
                Queen queen = hive.getQueen();
                hive.getHoney().honeyType(month, date.getDayOfMonth());
                double numberRandom = Math.random();

                if ((numberRandom < 0.5 && month.equals(HarvestingMonths.MAY) && date.getDayOfMonth() > 1 && date.getDayOfMonth() < 20) || queen.getAgeOfQueen() == 5) {
                    hive.changeQueen();
                }
                double whetherIndex = weather.weatherIndex(month, date.getDayOfMonth());
                int numberOfEggs = queen.makeEggs(honey, whetherIndex);
                hive.fillUpEggsFrame(date, numberOfEggs);
                hive.checkAndAddEggsToBees(date);
                hive.fillUpExistingHoneyFrameFromHive(date);
                hive.beesDie(date);
                hive.setKgOfHoney(hive.findTotalKgOfHoney());
                List<HoneyBatch> harvestedHoneyBatches = honey.harvestHoney(hive, month, date.getDayOfMonth());
                hive.addHoneyBatches(harvestedHoneyBatches, actionsOfTheWeek);
                hive.checkIfHiveCouldBeSplit(month, date.getDayOfMonth(), actionsOfTheWeek, lifeOfBeesGame);
                hive.checkIfCanAddNewEggsFrameInHive(actionsOfTheWeek);
                hive.checkIfCanAddANewHoneyFrameInHive(actionsOfTheWeek);
                hive.checkIfCanMoveAnEggsFrame(actionsOfTheWeek, lifeOfBeesGame);
                apiary.checkInsectControl(month, date.getDayOfMonth(), actionsOfTheWeek);
                apiary.checkFeedBees(month, date.getDayOfMonth(), actionsOfTheWeek);
            }
            apiary.honeyHarvestedByHoneyType();
            System.out.println("your honey harvested  until now:");
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
        return new LifeOfBees(apiary, gameId, gameName, location, date, todayWeather.getSpeedWind(), todayWeather.getTemperature(), todayWeather.getPrecipitation(), moneyInTheBank, totalKgOfHoneyHarvested, actionOfTheWeek);
    }

    public Integer getGameId() {
        return gameId;
    }


    public Apiary getApiary() {
        return apiary;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getSpeedWind() {
        return speedWind;
    }

    public double getPrecipitation() {
        return precipitation;
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