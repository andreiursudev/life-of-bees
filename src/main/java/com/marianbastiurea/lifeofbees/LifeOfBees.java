package com.marianbastiurea.lifeofbees;

import java.util.*;
import java.util.Date;
import java.util.List;


public class LifeOfBees {
    private Apiary apiary;// apiary is the place where it will be stored all hives
    private int hiveIdCounter = 1;

    public LifeOfBees() {
        this.apiary = new Apiary();
        createHives(apiary.getNumberOfHives());
    }

    @Override
    public String toString() {
        return "LifeOfBees{" +
                "apiary=" + apiary +
                ", hiveIdCounter=" + hiveIdCounter +
                '}';
    }

    // Method to create 10 hives and store them in the apiary
    public void createHives(int numberOfStartingHives) {

        List<Hive> hives = new ArrayList<>();
        for (int i = 1; i < numberOfStartingHives + 1; i++) {
            Hive hive = createHive();
            hives.add(hive);
        }
        apiary.setHives(hives);
        apiary.setNumberOfHives(numberOfStartingHives);
        System.out.println("First " + numberOfStartingHives + " are " + hives);
    }

    // Method to create a single hive with all its components
    private Hive createHive() {

        Random random = new Random();
        Hive hive = new Hive();
        hive.setItWasSplit(false);// a hive could be split only once in a year
        hive.setEggsBatches(new ArrayList<>());
        hive.setEggsFrames(new ArrayList<>());
        hive.setHoneyFrames(new ArrayList<>());
        hive.setBeesBatches(new ArrayList<>());
        hive.setHoneyBatches(new ArrayList<>());
        hive.setId(hiveIdCounter++);
        Honey honey = new Honey();
        hive.setHoney(honey);
        hive.getHoney().setHoneyType("Rapeseed");
        Queen queen = new Queen();
        hive.setQueen(queen);
        hive.getQueen().setAgeOfQueen(random.nextInt(1, 6));
        hive.setNumberOfHoneyFrame(random.nextInt(4, 6)); // Random number of honey frames
        hive.setNumberOfEggsFrame(random.nextInt(4, 6)); // Random number of eggs frames
        hive.setApiary(apiary);

        // Creating EggsFrame with a random number off eggs
        queen = new Queen(hive.getAgeOfQueen());
        for (int i = 1; i < hive.getNumberOfEggsFrame() + 1; i++) {
            int randomNumberOfEggs = random.nextInt(4000, 4500);
            hive.addEggsFrames(queen.fillUpWithEggs(i, randomNumberOfEggs));

        }

        // Creating HoneyFrames
        List<HoneyFrame> honeyFrames = new ArrayList<>();
        for (int i = 0; i < hive.getNumberOfHoneyFrame(); i++) {
            HoneyFrame honeyFrame = new HoneyFrame(random.nextDouble(2, 2.5), hive.getHoney().getHoneyType());
            honeyFrames.add(honeyFrame); // Add each HoneyFrame to the list
        }
        hive.addHoneyFrames(honeyFrames);

        int numberOfBees = random.nextInt(2000, 2500) * (hive.getNumberOfHoneyFrame() + hive.getNumberOfEggsFrame());
        hive.setNumberOfBees(numberOfBees);
        return hive;
    }

    // Method to iterate over 2 years and execute daily operations
    public void iterateOverTwoYears() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1); // Set the calendar to one year ago
        calendar.set(Calendar.MONTH, Calendar.APRIL); // Start the year on April 1st
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Iterate over 2 years
        for (int year = 0; year < 1; year++) {// Use only one for debug purposes
            while (calendar.get(Calendar.MONTH) != Calendar.JUNE) {
                // Iterate until OCTOBER
                Date currentDate = calendar.getTime();
                System.out.println("Date: " + currentDate);
                // Extract day of month and month from the calendar
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int monthValue = calendar.get(Calendar.MONTH);
                HarvestingMonths month = HarvestingMonths.values()[monthValue];


                List<Hive> hives = apiary.getHives();
                ArrayList<Hive> oldHives = new ArrayList<>(hives);
                for (Hive hive : oldHives) {
                    Queen queen = new Queen();
                    Honey honey=hive.getHoney();
                    hive.getHoney().honeyTypes(month, dayOfMonth);
                    hive.addEggsBatches(queen.makeBatchOfEggs(queen.makeEggs(hive, dayOfMonth, month), currentDate));
                    hive.fillUpExistingEggsFrameFromHive(currentDate);
                    hive.addNewEggsFrameInHive();
                    hive.checkAndAddEggsToBees(currentDate);
                    hive.fillUpExistingHoneyFrameFromHive(currentDate);
                    hive.addNewHoneyFrameInHive();
                    hive.addHoneyBatches(honey.makeHoneyBatch(hive,currentDate));
                    hive.beesDie(currentDate);
                    System.out.println();
                }

                calendar.add(Calendar.DAY_OF_MONTH, 1); // Move to the next day
            }

            List<Hive> hives = apiary.getHives();// have to build a hibernate method
            for (Hive hive : hives) {
                hive.setItWasSplit(false);
                hive.setAnswerIfWantToSplit(false);
            }


            apiary.honeyHarvestedByHoneyType();
            calendar.set(Calendar.MONTH, Calendar.APRIL);// Reset month for the next year
        }

        System.out.println("your apiary is: " + apiary);
        System.out.println("your honey production is " + apiary.getHarvestedHoneys());
    }
}