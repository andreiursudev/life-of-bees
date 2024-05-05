package com.marianbastiurea.lifeofbees;

import java.util.Date;
import java.util.*;
import java.util.Scanner;


public class Hive {
    private int id;
    private int numberOfHoneyFrame;
    private int numberOfEggsFrame;
    private List<EggsFrame> eggsFrames;
    private int numberOfBees;
    private Queen queen;
    private List<EggsBatch> eggsBatches;
    private Honey honey;
    private List<BeesBatch> beesBatches;
    private List<HoneyFrame> honeyFrames;
    private Apiary apiary; // Add an Apiary field to store the associated apiary

    public Hive(Apiary apiary,List <EggsBatch> eggsBatches, List<EggsFrame> eggsFrames, List<BeesBatch> beesBatches, List<HoneyFrame> honeyFrames) {
        this.apiary=apiary;
        this.eggsBatches = new ArrayList<>(eggsBatches);
        this.eggsFrames = new ArrayList<>(eggsFrames);
        this.beesBatches = new ArrayList<>(beesBatches);
        this.honeyFrames = new ArrayList<>(honeyFrames);
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", numberOfHoneyFrame=" + numberOfHoneyFrame +
                ", numberOfEggsFrame=" + numberOfEggsFrame +
                ", eggsFrames=" + eggsFrames +
                ", numberOfBees=" + numberOfBees +
                ", queen=" + queen +
                ", eggsBatches=" + eggsBatches +
                ", honey=" + honey +
                ", beesBatches=" + beesBatches +
                ", honeyFrames=" + honeyFrames +
                '}';
    }

    public void setEggsBatches(List<EggsBatch> eggsBatches) {
        this.eggsBatches = eggsBatches;
    }

    public void setEggsFrames(List<EggsFrame> eggsFrames) {
        this.eggsFrames = eggsFrames;
    }

    public void setBeesBatches(List<BeesBatch> beesBatches) {
        this.beesBatches = beesBatches;
    }

    public void setHoneyFrames(List<HoneyFrame> honeyFrames) {
        this.honeyFrames = honeyFrames;
    }

    public Queen getQueen() {
        return queen;
    }

    public void setQueen(Queen queen) {
        this.queen = queen;
    }

    public Hive() {
    }

    public Honey getHoney() {
        return honey;
    }

    public void setHoney(Honey honey) {
        this.honey = honey;
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


    public int getAgeOfQueen() {
        return getQueen().getAgeOfQueen();
    }

    public void setAgeOfQueen() {
        Random random = new Random();
        this.queen.setAgeOfQueen(random.nextInt(1, 5));
    }


    public int getNumberOfBees() {
        return numberOfBees;
    }

    public void setNumberOfBees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }


    public void addEggsBatches(List<EggsBatch> eggsBatches) {
        this.eggsBatches.addAll(eggsBatches);
    }

    public void addBeesBatches(List<BeesBatch> beesBatches) {
        this.beesBatches.addAll(beesBatches);
    }

    public void addHoneyFrames(List<HoneyFrame> honeyFrames) {
        this.honeyFrames.addAll(honeyFrames);
    }

    public void addEggsFrames(List<EggsFrame> eggsFrames) {
        this.eggsFrames.addAll(eggsFrames);
    }

    public List<EggsBatch> getEggsBatches() {
        return eggsBatches;
    }

    public List<HoneyFrame> getHoneyFrames() {
        return honeyFrames;
    }

    public List<EggsFrame> getEggsFrames() {
        return eggsFrames;
    }

    public List<BeesBatch> getBeesBatches() {
        return beesBatches;
    }

    public double ageOfQueenIndex() {
        /* a queen lives 3-5 years. When will build first 10 hives in apiary will use random to generate ageOfQueen
         between 1  and 5 years old for our queen. At age 5, will have to replace this queen with new one.
         Depending on age of queen will choose an fertility index between 0 and 1. When index is 0, queen is too old
         to lay eggs and she have to be replaced. Whenn index is 1,
     fertility of queen is at maximum and she can lay upon 2000 eggs daily
         */

        int ageOfQueen = this.getQueen().getAgeOfQueen();
        String honeyType = this.getHoney().getHoneyType();
        double numberRandom = Math.random();
        switch (ageOfQueen) {
            case 0, 1, 2, 3:
                return 1;
            case 4:
                if (numberRandom < 0.5 && honeyType == "Acacia") {
                    this.getQueen().setAgeOfQueen(0);
                    return 1;
                } else
                    return 0.75;
            case 5:
                this.getQueen().setAgeOfQueen(0);
                return 0.25;
            default:
                break;
        }
        return 0;
    }


    public void checkAndAddEggsToBees() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, -21);
        Date cutoffDate = calendar.getTime();
        List<EggsBatch> eggsBatches = this.getEggsBatches();
        List<EggsFrame> eggsFrames = this.getEggsFrames();
        List<BeesBatch> beesBatches = new ArrayList<>();

        // Iterate over each eggs batch
        for (EggsBatch eggsBatch : eggsBatches) {
            if (eggsBatch.getCreationDate().before(cutoffDate)) {
                int numberOfEggs = eggsBatch.getNumberOfEggs();
                this.numberOfBees += numberOfEggs; // Add eggs to bees
                for (EggsFrame eggsFrame : eggsFrames) {
                    eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() - (int) (numberOfEggs / eggsFrame.getNumberOfEggsFrame())); // Subtract eggs from frame
                    BeesBatch beesBatch = new BeesBatch(numberOfEggs, currentDate);
                    beesBatches.add(beesBatch); // Create and add bees batch
                }
            }
        }

        // Remove old eggs batches
        eggsBatches.clear();
        // Add new bees batches
        this.beesBatches.addAll(beesBatches);
    }


    public void fillUpExistingEggsFrameFromHive(Date currentDate) {

        int maxEggPerFrame = 6400; // a frame have around 8500 cells. 75% more or less are used by the queen to lay eggs.
        // Remaining cells are fill up with honey or are damaged

        System.out.println();
        Random random = new Random();
        Date getCreationDate = null;
        List<EggsBatch> eggsBatches = this.getEggsBatches();
        List<EggsFrame> eggsFrames = this.getEggsFrames();

        for (EggsBatch eggsBatch : eggsBatches) {
            if (currentDate.equals(eggsBatch.getCreationDate())) {
                getCreationDate = eggsBatch.getCreationDate();
                boolean eggsAdded = false;
                int eggsFrameFull = 0;
                EggsFrame eggsFrame = eggsFrames.get(eggsFrameFull);
                for (int i = 0; i < eggsFrames.size(); i++) {
                    while (eggsFrame.getNumberOfEggs() == maxEggPerFrame && eggsFrameFull < eggsFrames.size()) {
                        eggsFrameFull += 1;
                        if (eggsFrameFull < eggsFrames.size()) {
                            eggsFrame = eggsFrames.get(eggsFrameFull);
                        } else {
                            fillUpNewAddedEggsFrameInHive(getCreationDate);
                        }
                    }

                    if (!eggsAdded && maxEggPerFrame - eggsFrame.getNumberOfEggs() > eggsBatch.getNumberOfEggs()) {
                        eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() + eggsBatch.getNumberOfEggs());
                        eggsAdded = true;

                    } else if (!eggsAdded) {
                        if (eggsFrameFull < eggsFrames.size()) {
                            if (eggsFrameFull == eggsFrames.size() - 1) {
                                int eggsDifferenceToAddInCurrentEggsFrame = maxEggPerFrame - eggsFrame.getNumberOfEggs();
                                eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() + eggsDifferenceToAddInCurrentEggsFrame);
                                eggsAdded = true;
                            } else {
                                EggsFrame nextEggsFrame = eggsFrames.get(eggsFrameFull + 1);
                                int eggsDifferenceToAddInCurrentEggsFrame = maxEggPerFrame - eggsFrame.getNumberOfEggs();
                                int eggsToAddInNextEggsFrame = eggsBatch.getNumberOfEggs() - eggsDifferenceToAddInCurrentEggsFrame;
                                eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() + eggsDifferenceToAddInCurrentEggsFrame);
                                nextEggsFrame.setNumberOfEggs(nextEggsFrame.getNumberOfEggs() + eggsToAddInNextEggsFrame);
                                eggsAdded = true;
                            }
                        } else {
                            int eggsDifferenceToAddInCurrentEggsFrame = maxEggPerFrame - eggsFrame.getNumberOfEggs();
                            eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() + eggsDifferenceToAddInCurrentEggsFrame);
                            eggsAdded = true;
                        }
                    }
                }
            }
        }
        System.out.println("Hive ID: " + this.getId());
        System.out.println("Eggs Frame: " + this.getEggsFrames());
        System.out.println(" date is " + currentDate);
        System.out.println(" your hive is :" + this);
    }


    public void fillUpNewAddedEggsFrameInHive(Date getCreationDate) {
        int maxEggPerFrame = 6400;
        // a frame have around 8500 cells. 75% more or less are used by the queen to lay eggs.
        // Remaining cells are fill up with honey or are damaged
        Scanner scanner = new Scanner(System.in);
        Date date = getCreationDate;
        Calendar calendar = Calendar.getInstance();
        List<EggsBatch> eggsBatches = this.getEggsBatches();
        int maximumNumberOfFramesToAdd = 0;

        switch (this.getNumberOfEggsFrame()) {
            case 3, 5:
                maximumNumberOfFramesToAdd = 1;
                break;
            case 4:
                maximumNumberOfFramesToAdd = 2;
                break;
            case 6: {
                System.out.println("You hive is full");
                apiary.splitHive(this);
            }
            default:
                break;
        }

        int numberOfFramesToAdd;
        do {
            switch (this.getNumberOfEggsFrame()) {
                case 3: {
                    System.out.println("In hive you have 3 eggsFrame, maximum of eggsFrame which you can add will be 1\\n" +
                            "because hive is too small to care for more than another 1 frame.");
                }
                break;
                case 4, 5: {
                    System.out.println("you can insert another " + maximumNumberOfFramesToAdd + " eggsFrame");
                    System.out.println("You have to insert a number lower than or equal with " + maximumNumberOfFramesToAdd);
                }
                break;
                default:
                    break;
            }
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter an integer: ");
                scanner.next();
            }
            numberOfFramesToAdd = scanner.nextInt();
            if (numberOfFramesToAdd > maximumNumberOfFramesToAdd) {
                System.out.println("Input should be lower than or equal with " + maximumNumberOfFramesToAdd + "Please try again.");
            }
        }
        while (numberOfFramesToAdd > maximumNumberOfFramesToAdd);


            /*for a hive, maximum of eggsFrame for spring time is 6 frame.
             After queen fill this number of frame, we can choose to split the hive or add
            another 1/2 hive on top with frame just for honey and bottom hive numberOfEggsFrame will be 10
            and remaining 2 honeyFrame will be not harvested
             */

        for (int i = 1; i < numberOfFramesToAdd + 1; i++) {
            this.setNumberOfEggsFrame(this.getNumberOfEggsFrame() + 1);
            this.addEggsFrames(createNewEggsFrame(this.getNumberOfEggsFrame()));
        }

        List<EggsFrame> eggsFrames = this.getEggsFrames();

        for (EggsBatch eggsBatch : eggsBatches) {
            if (eggsBatch.getCreationDate().after(date)) {
                boolean eggsAdded = false;
                int eggsFrameFull = 0;
                EggsFrame eggsFrame = this.eggsFrames.get(eggsFrameFull);
                System.out.println("number of eggs in frame " + eggsFrame.getNumberOfEggsFrame() + " is " + eggsFrame.getNumberOfEggs());
                for (int i = 1; i < eggsFrames.size() + 1; i++) {
                    while (eggsFrame.getNumberOfEggs() == maxEggPerFrame && eggsFrameFull < eggsFrames.size()) {
                        eggsFrameFull += 1;
                        if (eggsFrameFull < eggsFrames.size()) {
                            eggsFrame = eggsFrames.get(eggsFrameFull);
                        } else {
                            System.out.println("You hive is full and will be split in two");
                            apiary.splitHive(this);
                        }
                    }

                    if (!eggsAdded && maxEggPerFrame - eggsFrame.getNumberOfEggs() > eggsBatch.getNumberOfEggs()) {
                        eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() + eggsBatch.getNumberOfEggs());
                        eggsAdded = true;

                    } else if (!eggsAdded) {
                        if (eggsFrameFull < eggsFrames.size()) {
                            if (eggsFrameFull == eggsFrames.size() - 1) {
                                int eggsDifferenceToAddInCurrentEggsFrame = maxEggPerFrame - eggsFrame.getNumberOfEggs();
                                eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() + eggsDifferenceToAddInCurrentEggsFrame);
                                eggsAdded = true;
                            } else {
                                EggsFrame nextEggsFrame = eggsFrames.get(eggsFrameFull + 1);
                                int eggsDifferenceToAddInCurrentEggsFrame = maxEggPerFrame - eggsFrame.getNumberOfEggs();
                                int eggsToAddInNextEggsFrame = eggsBatch.getNumberOfEggs() - eggsDifferenceToAddInCurrentEggsFrame;
                                eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() + eggsDifferenceToAddInCurrentEggsFrame);
                                nextEggsFrame.setNumberOfEggs(nextEggsFrame.getNumberOfEggs() + eggsToAddInNextEggsFrame);
                                eggsAdded = true;
                            }
                        } else {
                            int eggsDifferenceToAddInCurrentEggsFrame = maxEggPerFrame - eggsFrame.getNumberOfEggs();
                            eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() + eggsDifferenceToAddInCurrentEggsFrame);
                            eggsAdded = true;
                        }
                    }
                }

            }
        }
        System.out.println("Hive ID: " + this.getId());
        System.out.println("Eggs Frame: " + this.getEggsFrames());
        System.out.println(" date is " + date);
        System.out.println(" your hive is :" + this);
    }

    public List<EggsFrame> createNewEggsFrame(int numberOfEggsFrame) {
        List<EggsFrame> eggsFrames = new ArrayList<>();
        EggsFrame eggsFrame = new EggsFrame(numberOfEggsFrame, 0);
        eggsFrames.add(eggsFrame);
        return eggsFrames;
    }

}