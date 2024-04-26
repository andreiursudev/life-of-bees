package com.marianbastiurea.lifeofbees;

import java.util.*;
import java.util.Date;

public class LifeOfBees {
    private Apiary apiary;// apiary is the place where it will be stored all hives

    public LifeOfBees() {
        this.apiary = new Apiary();
    }

    // Method to create 10 hives and store them in the apiary
    public void createHives() {
        List<Hive> hives = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Hive hive = createHive();
            hives.add(hive);
        }
        apiary.setHives(hives);
    }

    // Method to create a single hive with all its components
    private Hive createHive() {
        Random random = new Random();
        Hive hive = new Hive();
        hive.setAgeOfQueen(random.nextInt(1, 5)); // Random age for the queen
        hive.setNumberOfHoneyFrame(random.nextInt(3, 6)); // Random number of honey frames
        hive.setNumberOfEggsFrame(random.nextInt(3, 6)); // Random number of eggs frames

        // Creating EggsFrame with a random number off eggs
        List<EggsFrame> eggsFrames = new ArrayList<>();
        for (int i = 0; i < hive.getNumberOfEggsFrame(); i++) {
            EggsFrame eggsFrame = new EggsFrame();
            eggsFrame.setEggs(random.nextInt(2000, 3000)); // Random number of eggs
            eggsFrames.add(eggsFrame);
        }
        hive.setEggsFrames(eggsFrames);
        // Creating HoneyFrames
        List<HoneyFrame> honeyFrames = new ArrayList<>();
        for (int i = 0; i < hive.getNumberOfHoneyFrame(); i++) {
            HoneyFrame honeyFrame = new HoneyFrame();
            honeyFrame.setKgOfHoney(random.nextDouble(1, 2)); // Random kg of honey
            honeyFrames.add(honeyFrame);
        }
        hive.setHoneyFrames(honeyFrames);


        // each hive will have a random number of bees for each frame
        int numberOfBees = random.nextInt(2000, 2500) * hive.getNumberOfHoneyFrame() * hive.getNumberOfEggsFrame();
        hive.setNumberOfBees(numberOfBees);

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
            while (calendar.get(Calendar.MONTH) != Calendar.OCTOBER) {
                // Iterate until OCTOBER
                Date currentDate = calendar.getTime();
                System.out.println("Date: " + currentDate);


                for (Hive hive : apiary.getHives()) {
                    Queen queen = new Queen();
                    hive.addEggsBatches(queen.makeBatchOfEggs(queen.makeEggs(), currentDate));
                    // Add eggs batches for the current day
                    hive.checkAndAddEggsToBees(); // Check and add eggs to the number of bees
                    System.out.println("Hive ID: " + hive.getId());
                    System.out.println("Eggs Batches: " + hive.getEggsBatches());
                    System.out.println("Number of Bees: " + hive.getNumberOfBees());
                    System.out.println();
                }


                // Handle honey production and storage
                // (Add honey type and quantity to the apiary's honey list)
//                        String honeyType = HoneyFrame.honeyTypes(month, dayOfMonth);
//                        double honeyKg = hive.getHoneyProduction(honeyType);
//                        addHoneyToApiary(honeyType, honeyKg);
//                    }

                calendar.add(Calendar.DAY_OF_MONTH, 1); // Move to the next day
            }
            calendar.set(Calendar.MONTH, Calendar.MARCH); // Reset month for the next year
        }
    }

    // Method to add honey type and quantity to the apiary's honey list
    private void addHoneyToApiary(String honeyType, double honeyKg) {
        // Implement adding honey to the apiary's list
        // You may need to instantiate the Honey object and add it to the list
    }

    public static void main(String[] args) {
        LifeOfBees lifeOfBees = new LifeOfBees();
        lifeOfBees.createHives();
        lifeOfBees.iterateOverTwoYears();
    }
}


