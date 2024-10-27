package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static com.marianbastiurea.lifeofbees.ActionOfTheWeek.addOrUpdateAction;
import static com.marianbastiurea.lifeofbees.ActionOfTheWeek.addOrUpdateAction1;
import static com.marianbastiurea.lifeofbees.Honey.getHarvestingMonth;


public class LifeOfBees {
    private Apiary apiary;// apiary is the place where it will be stored all hives
    private int hiveIdCounter = 1;
    private List<ActionOfTheWeek> actionOfTheWeek;
    private Integer gameId;
    private String gameName;
    private String location;
    private String startingDate;
    private String currentDate;
    private int numberOfStartingHives;
    private double speedWind;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;
    private double moneyInTheBank;
    private double totalKgOfHoneyHarvested;


    public LifeOfBees(Apiary apiary, Integer gameId,
                      String gameName, String location, String currentDate,
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
                ", hiveIdCounter=" + hiveIdCounter +
                ", action=" + actionOfTheWeek +
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
                ", totalKgOfHoneyHarvested=" + totalKgOfHoneyHarvested +
                '}';
    }


    public LifeOfBees iterateOneWeek(LifeOfBees lifeOfBeesGame) {
        LocalDate date = LocalDate.parse(lifeOfBeesGame.getCurrentDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        HarvestingMonths month = getHarvestingMonth(date);
        Whether whether = new Whether();

        List<ActionOfTheWeek> actionsOfTheWeek = new ArrayList<>();


        Whether todayWeather = null;
        for (int dailyIterator = 0; dailyIterator < 7; dailyIterator++) {
            System.out.println("today is " + date.getDayOfMonth());
            System.out.println("daily iterator is: " + dailyIterator);
            todayWeather = whether.whetherToday(month, date.getDayOfMonth());
            List<Hive> hives = apiary.getHives();
            apiary.setNumberOfHives(hives.size());
            ArrayList<Hive> oldHives = new ArrayList<>(hives);
            List<Integer> hiveIds = new ArrayList<>();
            for (Hive hive : oldHives) {
                Queen queen = hive.getQueen();
                hive.getHoney().honeyType(month, date.getDayOfMonth());
                double numberRandom = Math.random();

                if ((numberRandom < 0.5 && month.equals(HarvestingMonths.MAY) && date.getDayOfMonth() > 1 && date.getDayOfMonth() < 20) || queen.getAgeOfQueen() == 5) {
                    hive.changeQueen();
                }

                Honey honey = hive.getHoney();
                double whetherIndex = whether.whetherIndex(month, date.getDayOfMonth());
                int numberOfEggs = queen.makeEggs(honey, whetherIndex);

                hive.fillUpEggsFrame(date, numberOfEggs);
                hive.checkAndAddEggsToBees(date);
                hive.fillUpExistingHoneyFrameFromHive(date);
                hive.beesDie(date);

                if (hive.checkIfCanAddNewEggsFrameInHive()) {
                    Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("ADD_EGGS_FRAME", actionsOfTheWeek).getData();
                    addOrUpdateAction("ADD_EGGS_FRAME", hive.getId(), data, actionsOfTheWeek);
                }

                if (hive.checkIfCanAddANewHoneyFrameInHive()) {
                    Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("ADD_HONEY_FRAME", actionsOfTheWeek).getData();
                    addOrUpdateAction("ADD_HONEY_FRAME", hive.getId(), data, actionsOfTheWeek);
                }

                if (hive.checkIfHiveCouldBeSplit(month, date.getDayOfMonth())) {
                    Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("SPLIT_HIVE", actionsOfTheWeek).getData();
                    addOrUpdateAction("SPLIT_HIVE", hive.getId(), data, actionsOfTheWeek);
                }


                hive.addHoneyBatches(honey.harvestHoney(hive, month,date.getDayOfMonth()));
                hive.setKgOfHoney(hive.findTotalKgOfHoney());
                if(hive.getKgOfHoney()>0){
                    Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("HARVEST_HONEY", actionsOfTheWeek).getData();
                    addOrUpdateAction("HARVEST_HONEY", hive.getId(), data, actionsOfTheWeek);
                }

                if(apiary.checkInsectControl(month,date.getDayOfMonth())){
                    Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("INSECT_CONTROL", actionsOfTheWeek).getData();
                    addOrUpdateAction("INSECT_CONTROL", apiary.getNumberOfHives(), data, actionsOfTheWeek);
                }

                if(apiary.checkFeedBees(month,date.getDayOfMonth())){
                    Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("FEED_BEES", actionsOfTheWeek).getData();
                    addOrUpdateAction("FEED_BEES", apiary.getNumberOfHives(), data, actionsOfTheWeek);
                }


                List<List<Integer>> hiveIdPairs = hive.checkIfCanMoveAnEggsFrame();
                if (!hiveIdPairs.isEmpty()) {
                    Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("MOVE_EGGS_FRAME", actionsOfTheWeek).getData();

                    // Adăugăm o acțiune pentru fiecare pereche de ID-uri de stupi
                    for (List<Integer> hiveIdPair : hiveIdPairs) {
                        addOrUpdateAction1("MOVE_EGGS_FRAME", hiveIdPair, data, actionsOfTheWeek);
                    }
                }



            }
            lifeOfBeesGame.setActionOfTheWeek(actionsOfTheWeek);

            apiary.honeyHarvestedByHoneyType();
            System.out.println("your honey harvested  is:");

            if (date.isEqual(LocalDate.of(date.getYear(), 9, 30))) {
                for (Hive hive : hives) {
                    apiary.hibernate(hive);
                }

                // Setăm noua dată pentru începutul următorului an
                date = LocalDate.of(date.getYear() + 1, 3, 1);
                System.out.println("Your apiary at the end of the year is: " + apiary);

                // Creăm sau actualizăm acțiunea "HIBERNATE" pentru începutul perioadei de hibernare
                Map<String, Object> data = new HashMap<>();
                data.put("totalHives", apiary.getHives().size());
                data.put("hibernateStartDate", date.toString());

                ActionOfTheWeek.addOrUpdateAction("HIBERNATE", 0, data, actionsOfTheWeek);

                // Actualizăm acțiunile săptămânii în joc
                lifeOfBeesGame.setActionOfTheWeek(actionsOfTheWeek);
                break;
            }


            System.out.println("Action of the day " + date.getDayOfMonth() + " is " + lifeOfBeesGame.getActionOfTheWeek());
            date = date.plusDays(1); // Actualizează data
            month = getHarvestingMonth(date);

        }


        String newCurrentDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        lifeOfBeesGame.setCurrentDate(newCurrentDate);
        double totalKgOfHoneyHarvested = apiary.honeyHarvestedByHoneyType();
        return new LifeOfBees(apiary, gameId, gameName, location, newCurrentDate, todayWeather.getSpeedWind(), todayWeather.getTemperature(), todayWeather.getPrecipitation(), moneyInTheBank, totalKgOfHoneyHarvested, actionOfTheWeek);
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

    public double getTotalKgOfHoneyHarvested() {
        return totalKgOfHoneyHarvested;
    }

    public void setTotalKgOfHoneyHarvested(double totalKgOfHoneyHarvested) {
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public List<ActionOfTheWeek> getActionOfTheWeek() {
        return actionOfTheWeek;
    }

    public void setActionOfTheWeek(List<ActionOfTheWeek> actionOfTheWeek) {
        this.actionOfTheWeek = actionOfTheWeek;
    }
}