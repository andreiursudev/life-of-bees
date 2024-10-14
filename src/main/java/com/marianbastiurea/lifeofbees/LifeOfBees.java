package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static com.marianbastiurea.lifeofbees.Honey.getHarvestingMonth;


public class LifeOfBees {
    private Apiary apiary;// apiary is the place where it will be stored all hives
    private int hiveIdCounter = 1;
    private Action action;
    private Integer gameId;
    private String gameName;
    private String location;
    private String startingDate;
    private String currentDate;
    private int numberOfStartingHives;
    private double speedWind;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;
    private String actionOfTheWeek;
    private double moneyInTheBank;
    private double totalKgOfHoney;

    public LifeOfBees(Apiary apiary, Integer gameId, String gameName, String location, String currentDate, double speedWind, double temperature, double precipitation, String actionOfTheWeek, double moneyInTheBank, double totalKgOfHoney) {
        this.apiary = apiary;
        this.gameId = gameId;
        this.gameName = gameName;
        this.location = location;
        this.currentDate = currentDate;
        this.speedWind = speedWind;
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.moneyInTheBank = moneyInTheBank;
        this.action = new Action();
        this.totalKgOfHoney = totalKgOfHoney;

        String[] dateParts = currentDate.split("-");
        int month = Integer.parseInt(dateParts[1]);
        int dayOfMonth = Integer.parseInt(dateParts[2]);

        HarvestingMonths harvestingMonth = HarvestingMonths.values()[month - 1];
        this.actionOfTheWeek = this.action.actionType(harvestingMonth, dayOfMonth);
    }

    @Override
    public String toString() {
        return "LifeOfBees{" +
                "apiary=" + apiary +
                ", hiveIdCounter=" + hiveIdCounter +
                ", action=" + action +
                ", gameId=" + gameId +
                ", gameName='" + gameName + '\'' +
                ", location='" + location + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", numberOfStartingHives=" + numberOfStartingHives +
                ", speedWind=" + speedWind +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                ", actionOfTheWeek='" + actionOfTheWeek + '\'' +
                ", moneyInTheBank=" + moneyInTheBank +
                ", totalKgOfHoney=" + totalKgOfHoney+
                '}';
    }

    // Method to iterate over one week and execute daily operations
    public void iterateOneWeek(String currentDate) {

        LocalDate date = LocalDate.parse(currentDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        int day = date.getDayOfMonth();
        HarvestingMonths month = getHarvestingMonth(date);

        Whether whether = new Whether();
        Whether todayWeather = whether.whetherToday(month, day);
        double speedWind = todayWeather.getSpeedWind();
        double temperature = todayWeather.getTemperature();
        double precipitation = todayWeather.getPrecipitation();

        for (int dailyIterator = 0; dailyIterator < 6; dailyIterator++) {
            while (!month.equals(HarvestingMonths.OCTOBER)) {
                List<Hive> hives = apiary.getHives();
                apiary.setNumberOfHives(apiary.getHives().size());
                ArrayList<Hive> oldHives = new ArrayList<>(hives);
                for (Hive hive : oldHives) {
                    Queen queen = hive.getQueen();
                    hive.getHoney().honeyType(month, day);
                    double numberRandom = Math.random();
                    if (numberRandom < 0.5 && month.equals(HarvestingMonths.MAY) && day > 1 && day < 20 || queen.getAgeOfQueen() == 5) {
                        hive.changeQueen();
                    }
                    Honey honey = hive.getHoney();
                    double whetherIndex = whether.whetherIndex(month, day);

                    int numberOfEggs = queen.makeEggs(honey, whetherIndex);
                    hive.fillUpEggsFrame(date, numberOfEggs);
//                    hive.addNewEggsFrameInHive(year);
//                    hive.moveAnEggsFrameFromUnsplitHiveToASplitOne();
//                    hive.checkAndAddEggsToBees(currentDate);
//                    hive.fillUpExistingHoneyFrameFromHive(currentDate);
//                    hive.addNewHoneyFrameInHive();
//                    hive.addHoneyBatches(honey.makeHoneyBatch(hive, currentDate));
//                    hive.beesDie(currentDate);


                    System.out.println();
                }

                date = date.plusDays(1);// Move to the next day
                month = getHarvestingMonth(date);
            }

            apiary.honeyHarvestedByHoneyType();
            // System.out.println("your apiary at the end of the year " + year + " is: " + apiary);

            List<Hive> hives = apiary.getHives();// have to build a hibernate method

            for (Hive hive : hives) {
                apiary.hibernate(hive);
            }

            // calendar.set(Calendar.MONTH, Calendar.MARCH);// Reset month for the next year

            System.out.println("your apiary at the beginning of new  year is: " + apiary);
        }
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

    public Action getAction() {
        return action;
    }

    public double getSpeedWind() {
        return speedWind;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public String getActionOfTheWeek() {
        return actionOfTheWeek;
    }

    public double getMoneyInTheBank() {
        return moneyInTheBank;
    }

    public void setMoneyInTheBank(double moneyInTheBank) {
        this.moneyInTheBank = moneyInTheBank;
    }

    public int getHiveIdCounter() {
        return hiveIdCounter;
    }

    public String getGameName() {
        return gameName;
    }

    public String getLocation() {
        return location;
    }

    public int getNumberOfStartingHives() {
        return numberOfStartingHives;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public double getTotalKgOfHoney() {
        return totalKgOfHoney;
    }

    public void setTotalKgOfHoney(double totalKgOfHoney) {
        this.totalKgOfHoney = totalKgOfHoney;
    }
}