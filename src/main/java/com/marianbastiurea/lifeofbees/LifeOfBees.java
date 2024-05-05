package com.marianbastiurea.lifeofbees;

import java.util.*;
import java.util.Date;
import java.util.List;


public class LifeOfBees {
    private Apiary apiary;// apiary is the place where it will be stored all hives
    private int hiveIdCounter = 1;

    public LifeOfBees() {
        this.apiary = new Apiary();
    }

    // Method to create 10 hives and store them in the apiary
    public void createHives(int numberOfStartingHives) {
        List<Hive> hives = new ArrayList<>();
        for (int i = 1; i < numberOfStartingHives + 1; i++) {
            Hive hive = createHive();
            hives.add(hive);
        }
        apiary.setHives(hives);
        System.out.println("First " + numberOfStartingHives + " are " + hives);
    }

    // Method to create a single hive with all its components
    private Hive createHive() {
        Random random = new Random();
        Hive hive = new Hive();
        hive.setEggsBatches(new ArrayList<>());
        hive.setEggsFrames(new ArrayList<>());
        hive.setHoneyFrames(new ArrayList<>());
        hive.setId(hiveIdCounter++);
        Queen queen = new Queen();
        hive.setQueen(queen);
        hive.getQueen().setAgeOfQueen(random.nextInt(1, 5));
        hive.setNumberOfHoneyFrame(random.nextInt(3, 6)); // Random number of honey frames
        hive.setNumberOfEggsFrame(random.nextInt(3, 6)); // Random number of eggs frames

        // Creating EggsFrame with a random number off eggs
        queen = new Queen(hive.getAgeOfQueen());
        for (int i = 1; i < hive.getNumberOfEggsFrame() + 1; i++) {
            int randomNumberOfEggs = random.nextInt(2000, 3000);
            hive.addEggsFrames(queen.fillUpWithEggs(i, randomNumberOfEggs));
        }
        System.out.println("Hive ID: " + hive.getId());
        System.out.println("Eggs Frame: " + hive.getEggsFrames());
        System.out.println();


        // Creating HoneyFrames
        for (int i = 0; i < hive.getNumberOfHoneyFrame(); i++) {
            List<HoneyFrame> honeyFrames = new ArrayList<>();
            HoneyFrame honeyFrame = new HoneyFrame(random.nextDouble(2, 3), "Wildflower");
            honeyFrames.add(honeyFrame);
            hive.addHoneyFrames(honeyFrames);
        }

        // each hive will have a random number of bees for each frame
        int numberOfBees = random.nextInt(2000, 2500) * (hive.getNumberOfHoneyFrame() + hive.getNumberOfEggsFrame());
        hive.setNumberOfBees(numberOfBees);
        System.out.println("Hive " + hive + " have a starting number of bees: " + hive.getNumberOfBees());

        return hive;
    }

    // Method to iterate over 2 years and execute daily operations
    public void iterateOverTwoYears() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1); // Set the calendar to one year ago
        calendar.set(Calendar.MONTH, Calendar.MARCH); // Start the year on March 1st
        calendar.set(Calendar.DAY_OF_MONTH, 1);


        // Iterate over 2 years
        for (int year = 0; year < 1; year++) {// Use only one for debug purposes
            while (calendar.get(Calendar.MONTH) != Calendar.APRIL) {
                // Iterate until OCTOBER
                Date currentDate = calendar.getTime();
                System.out.println("Date: " + currentDate);
                // Extract day of month and month from the calendar
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                HarvestingMonths month = HarvestingMonths.values()[calendar.get(Calendar.MONTH)];


                for (Hive hive : apiary.getHives()) {
                    Queen queen = new Queen();
                    double queenIndex = hive.ageOfQueenIndex(dayOfMonth, month);
                    hive.addEggsBatches(queen.makeBatchOfEggs(queen.makeEggs(hive,dayOfMonth,month), currentDate));
                    hive.fillUpExistingEggsFrameFromHive(currentDate);
                    hive.checkAndAddEggsToBees();
                    // Add eggs batches for the current day
                    hive.checkAndAddEggsToBees(); // Check and add eggs to the number of bees
                    System.out.println();


                }


                calendar.add(Calendar.DAY_OF_MONTH, 1); // Move to the next day
            }
            calendar.set(Calendar.MONTH, Calendar.MARCH); // Reset month for the next year
        }
    }
}