package com.marianbastiurea.lifeofbees;

import java.util.*;


public class EggFrames {

    private int daysToHatch = 20;
    public final static int maxEggPerFrame = 6400;
    private int numberOfEggFrames;
    private LinkedList<Integer> eggsByDay;
    private int maxEggFrames = 6;

    public EggFrames(int numberOfEggFrames, LinkedList<Integer> eggsByDay) {
        this.numberOfEggFrames = numberOfEggFrames;
        this.eggsByDay = eggsByDay;
    }

    public EggFrames(int numberOfEggFrames) {
        this.numberOfEggFrames = numberOfEggFrames;

        this.eggsByDay = new LinkedList<>(Collections.nCopies(daysToHatch, 0));
    }

    //static factory
    public static EggFrames getRandomEggFrames() {
        Random random = new Random();
        EggFrames eggFrames = new EggFrames(random.nextInt(2, 5));
        for (int j = 0; j < 20; j++) {
            eggFrames.ageOneDay(random.nextInt(101, 901));
        }
        return eggFrames;
    }

    public int getNumberOfEggFrames() {
        return numberOfEggFrames;
    }

//TODO write unit test
    public EggFrames splitEggFrames() {
        LinkedList<Integer> newEggBatches = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            newEggBatches.add(eggsByDay.get(i) / 2);
            eggsByDay.add(eggsByDay.get(i) / 2);
        }
        numberOfEggFrames = 3;
        return new EggFrames(3, newEggBatches);
    }

    public List<Integer> extractEggBatchesForFrame() {
        int currentSum = 0;
        int index = 0;
        while (index < eggsByDay.size() && currentSum + eggsByDay.get(index) <= maxEggPerFrame) {
            currentSum += eggsByDay.get(index);
            index++;
        }
        List<Integer> extractedBatches = new ArrayList<>(eggsByDay.subList(0, index));
        eggsByDay.subList(0, index).clear();
        numberOfEggFrames--;

        return extractedBatches;
    }

    public void addEggBatches(List<Integer> batchesToAdd) {
        eggsByDay.addAll(batchesToAdd);
        numberOfEggFrames++;
    }

    //TODO split this method in two to decrease coupling,
    public void moveAnEggsFrameFromOneHiveToAnother(Hive sourceHive, Hive destinationHive) {
        EggFrames sourceEggFrames = sourceHive.getEggFrames();
        EggFrames destinationEggFrames = destinationHive.getEggFrames();
        List<Integer> eggBatchesToMove = sourceEggFrames.extractEggBatchesForFrame();
        destinationEggFrames.addEggBatches(eggBatchesToMove);
    }

    public boolean isFull() {
        int totalEggs = getEggs();
        return totalEggs >= maxEggPerFrame * numberOfEggFrames;
    }


    private int getEggs() {
        return eggsByDay.stream().mapToInt(Integer::intValue).sum();
    }

//TODO add unit test and fix age to hatch
    public void removeLastTwoEggBatches() {
        eggsByDay.removeLast();
        eggsByDay.removeLast();
    }

    public boolean is80PercentFull() {
        int totalEggs = getEggs();
        return totalEggs >= numberOfEggFrames * maxEggPerFrame * 0.8;
    }

    public int ageOneDay(int eggsToAdd) {
        int currentEggs = getEggs();
        int maxEggs = maxEggPerFrame * numberOfEggFrames;
        eggsByDay.addFirst(Math.min(eggsToAdd, maxEggs - currentEggs));
        return eggsByDay.removeLast();
    }

    public void incrementNumberOfEggFrames() {
        if (this.numberOfEggFrames < 6) {
            this.numberOfEggFrames++;
        }
    }

    public void adjustNumberOfEggFramesAfterSplit(int remainingFrames) {
        if (remainingFrames >= 0 && remainingFrames <= this.numberOfEggFrames) {
            this.numberOfEggFrames = remainingFrames;
        }
    }



    public boolean isFullEggFrames() {
        return numberOfEggFrames == maxEggFrames;
    }

    @Override
    public String toString() {
        return "EggFrames{" +
                "numberOfEggFrames=" + numberOfEggFrames +
                ", eggBatches=" + eggsByDay +
                '}';
    }
}
