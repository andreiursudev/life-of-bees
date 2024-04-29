package com.marianbastiurea.lifeofbees;

import java.util.Date;
import java.util.*;


public class Hive {
    private int id;
    private int numberOfHoneyFrame;
    private int numberOfEggsFrame;
    private int ageOfQueen;
    private int numberOfEggs;
    private List<EggsFrame> eggsFrames;
    private HoneyFrame honeyFrame;
    private int numberOfBees;
    private double honeyQuantity;
    private Queen queen;
    private Bees bees;
    private List<EggsBatch> eggsBatches;

    public Hive(List<EggsBatch> eggsBatches, List<EggsFrame> eggsFrames) {
        this.eggsBatches = new ArrayList<>(eggsBatches);
        this.eggsFrames = new ArrayList<>(eggsFrames);
    }

    public void setEggsBatches(List<EggsBatch> eggsBatches) {
        this.eggsBatches = eggsBatches;
    }

    public void setEggsFrames(List<EggsFrame> eggsFrames) {
        this.eggsFrames = eggsFrames;
    }

    public Bees getBees() {
        return bees;
    }

    public void setBees(Bees bees) {
        this.bees = bees;
    }

    public Queen getQueen() {
        return queen;
    }

    public void setQueen(Queen queen) {
        this.queen = queen;
    }

    public Hive() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHoneyFrame() {
        return numberOfHoneyFrame;
    }

    public void setNumberOfHoneyFrame(int numberOfHoneyFrame) {
        this.numberOfHoneyFrame = numberOfHoneyFrame;
    }

    public int getNumberOfEggsFrame() {
        return numberOfEggsFrame;
    }

    public void setNumberOfEggsFrame(int numberOfEggsFrame) {
        this.numberOfEggsFrame = numberOfEggsFrame;
    }

    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    public void setNumberOfEggs(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }

    public int getAgeOfQueen() {
        return getQueen().getAgeOfQueen();
    }

    public void setAgeOfQueen() {
        Random random = new Random();
        this.queen.setAgeOfQueen(random.nextInt(1, 5));
    }


    public HoneyFrame getHoneyFrame() {
        return honeyFrame;
    }

    public void setHoneyFrame(HoneyFrame honeyFrame) {
        this.honeyFrame = honeyFrame;
    }

    public int getNumberOfBees() {
        return numberOfBees;
    }

    public void setNumberOfBees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }

    public double getHoneyQuantity() {
        return honeyQuantity;
    }

    public void setHoneyQuantity(double honeyQuantity) {
        this.honeyQuantity = honeyQuantity;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", numberOfHoneyFrame=" + numberOfHoneyFrame +
                ", numberOfEggsFrame=" + numberOfEggsFrame +
                ", ageOfQueen=" + ageOfQueen +
                ", eggsFrames=" + eggsFrames +
                ", honeyFrame=" + honeyFrame +
                ", numberOfBees=" + numberOfBees +
                ", honeyQuantity=" + honeyQuantity +
                ", eggsBatches=" + eggsBatches +
                '}';
    }


    public void setHoneyFrames(List<HoneyFrame> honeyFrames) {

    }

    public void addEggsBatches(List<EggsBatch> eggsBatches) {
        this.eggsBatches.addAll(eggsBatches);
    }

    public void addEggsFrames(List<EggsFrame> eggsFrames) {
        this.eggsFrames.addAll(eggsFrames);
    }

    public List<EggsBatch> getEggsBatches() {
        return eggsBatches;
    }

    public List<EggsFrame> getEggsFrames() {
        return eggsFrames;
    }

    public double ageOfQueenIndex(Hive hive) {
        /* a queen lives 3-5 years. When will build first 10 hives in apiary will use random to generate ageOfQueen
         between 1  and 5 years old for our queen. At age 5, will have to replace this queen with new one.
         Depending on age of queen will choose an fertility index between 0 and 1. When index is 0, queen is too old
         to lay eggs and she have to be replaced. Whenn index is 1,
     fertility of queen is at maximum and she can lay upon 2000 eggs daily
         */
        //int ageOfQueen =getQueen().get
        int ageOfQueen = hive.getQueen().getAgeOfQueen();
        double numberRandom = Math.random();
        switch (ageOfQueen) {
            case 0, 1, 2, 3:
                return 1;
            case 4:
                if (numberRandom < 0.5) {
                    hive.getQueen().setAgeOfQueen(0);
                    return 1;
                } else
                    return 0.75;
            case 5:
                hive.getQueen().setAgeOfQueen(0);
                return 0.25;
            default:
                break;
        }
        return 0;
    }


    public void checkAndAddEggsToBees() {
        // after 21 days eggs hatch in bees. this method will check date when eggsBatch was laid
        //and add the number of eggs to bee number from hive.

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime(); // Get current date

        // Set calendar to 21 days ago
        calendar.add(Calendar.DAY_OF_MONTH, -21);
        Date cutoffDate = calendar.getTime();

        // Iterate over each eggs batch
        for (EggsBatch eggsBatch : eggsBatches) {
            // Check if the batch date is before the cutoff date
            if (eggsBatch.getCreationDate().before(cutoffDate)) {
                // Add the number of eggs to the number of bees
                numberOfBees += eggsBatch.getNumberOfEggs();
            }
        }

    }

    public void fillUpExistingEggsFrameFromHive(Hive hive) {
        int maxEggPerFrame = 6400;
        int totalNumberOfEggsPerHiveAtBeginning = hive.getNumberOfEggs();
//        System.out.println("Total number of eggs at beginning: " + totalNumberOfEggsPerHiveAtBeginning);
//        System.out.println("number of eggsFrame " + hive.getNumberOfEggsFrame());
        System.out.println(" maximum number of eggs for initial eggs frame " + maxEggPerFrame * hive.getNumberOfEggsFrame());
        int totalNumberOfEggsPerHive = totalNumberOfEggsPerHiveAtBeginning;
        int count = 0;
        Date getCreationDate = null;
        List<EggsBatch> eggsBatches = hive.getEggsBatches();
        do {
            for (EggsBatch eggsBatch : eggsBatches) {
                count = eggsBatches.size();
                int getEggsNumber = eggsBatch.getNumberOfEggs();
                getCreationDate = eggsBatch.getCreationDate();
                totalNumberOfEggsPerHive += getEggsNumber;
                    System.out.println("Total number of eggs with eggsBatch: " + totalNumberOfEggsPerHive);
                // System.out.println("date when initial eggs frame are full"+eggsBatch.getCreationDate());
                count++;
            }
        }
        while (count < eggsBatches.size());
        if (totalNumberOfEggsPerHive > maxEggPerFrame * hive.getNumberOfEggsFrame()) {
            if (hive.getNumberOfEggsFrame() < 6) {
                int numberOfFramesToAdd = 6 - hive.getNumberOfEggsFrame();
                System.out.println("today is " + getCreationDate);
                System.out.println("you can insert another " + numberOfFramesToAdd+" eggsFrame");
                //add others eggsFrame in hive
            }
        }

    }
    // codul de mai jos nu e terminat.
    public void fillUpNewAddedEggsFrameInHive(Hive hive){
        int maxEggPerFrame = 6400;
        int numberOfFramesToAdd = 6 - hive.getNumberOfEggsFrame();// value could be an input
        Queen queen = hive.getQueen();
        for (int i = 1; i < numberOfFramesToAdd + 1; i++) {
            hive.setNumberOfEggsFrame(hive.getNumberOfEggsFrame() + 1);
            hive.addEggsFrames(queen.fillUpWithEggs(hive.getNumberOfEggsFrame(), 0));
        }
        System.out.println("Hive ID: " + hive.getId());
        System.out.println("Eggs Frame: " + hive.getEggsFrames());
        // System.out.println("total number of Hive's eggs for start " + hive.getNumberOfEggs());
        // now we calculate how many other eggsBatch it's needed to fill up new eggsFrames
        int numbersOfEggsBatchedNeeded=(int)numberOfFramesToAdd*maxEggPerFrame;

    }
}

