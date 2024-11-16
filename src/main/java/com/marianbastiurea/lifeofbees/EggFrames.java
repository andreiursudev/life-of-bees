package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class EggFrames {

    private final int maxEggPerFrame = 6400;
    private int numberOfEggFrames;
    LinkedList<Integer> eggBatches = new LinkedList<>();

    public EggFrames(int numberOfEggFrames, LinkedList<Integer> eggBatches) {
        this.numberOfEggFrames = numberOfEggFrames;
        this.eggBatches = eggBatches;
    }

    public EggFrames() {
    }

    public int getNumberOfEggFrames() {
        return numberOfEggFrames;
    }

    public EggFrames createEggFrames() {
        Random random = new Random();
        int numberOfEggFrames = random.nextInt(2) + 3;
        LinkedList<Integer> eggBatches = new LinkedList<>();
        for (int j = 0; j < 21; j++) {
            eggBatches.add(random.nextInt(101) + 800);
        }
        return new EggFrames(numberOfEggFrames, eggBatches);
    }

    public EggFrames splitEggFrames() {
        LinkedList<Integer> newEggBatches = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            newEggBatches.add(eggBatches.removeFirst());
        }
        return new EggFrames(3, newEggBatches);
    }

    public List<Integer> extractEggBatchesForFrame() {
        int currentSum = 0;
        int index = 0;
        while (index < eggBatches.size() && currentSum + eggBatches.get(index) <= maxEggPerFrame) {
            currentSum += eggBatches.get(index);
            index++;
        }
        List<Integer> extractedBatches = new ArrayList<>(eggBatches.subList(0, index));
        eggBatches.subList(0, index).clear();
        numberOfEggFrames--;

        return extractedBatches;
    }

    public void addEggBatches(List<Integer> batchesToAdd) {
        eggBatches.addAll(batchesToAdd);
        numberOfEggFrames++;
    }

    public void moveAnEggsFrameFromOneHiveToAnother(Hive sourceHive, Hive destinationHive) {
        EggFrames sourceEggFrames = sourceHive.getEggFrames();
        EggFrames destinationEggFrames = destinationHive.getEggFrames();
        List<Integer> eggBatchesToMove = sourceEggFrames.extractEggBatchesForFrame();
        destinationEggFrames.addEggBatches(eggBatchesToMove);
    }

    public boolean isFull() {
        int totalEggs = eggBatches.stream().mapToInt(Integer::intValue).sum();
        return totalEggs >= maxEggPerFrame * numberOfEggFrames;
    }


    public void fillUpAnEggFrames(int numberOfEggs) {
        int totalEggs = eggBatches.stream().mapToInt(Integer::intValue).sum();
        int maxCapacity = maxEggPerFrame * numberOfEggFrames;
        if (totalEggs + numberOfEggs <= maxCapacity) {
            eggBatches.addFirst(numberOfEggs);
        } else {
            eggBatches.addFirst(maxCapacity - totalEggs);
        }
    }


    public void removeLastTwoEggBatches() {
        eggBatches.removeLast();
        eggBatches.removeLast();
    }

    public boolean is80PercentFull() {
        int totalEggs = eggBatches.stream().mapToInt(Integer::intValue).sum();
        return totalEggs >= numberOfEggFrames * maxEggPerFrame * 0.8;
    }

    public int checkAndHatchEggs() {
        return eggBatches.removeLast();
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

    @Override
    public String toString() {
        return "EggFrames{" +
                "numberOfEggFrames=" + numberOfEggFrames +
                ", eggBatches=" + eggBatches +
                '}';
    }
}
