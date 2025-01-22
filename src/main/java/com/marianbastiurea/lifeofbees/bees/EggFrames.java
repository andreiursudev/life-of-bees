package com.marianbastiurea.lifeofbees.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class EggFrames {

    public final static int maxEggPerFrame = 6400;
    private int numberOfEggFrames;
    private LinkedList<Integer> eggsByDay;
    private static final int daysToHatch = 20;
    public boolean wasMovedAnEggsFrame;
    int maxNumberOfEggFrames = 6;
    private static final Logger logger = LoggerFactory.getLogger(Apiary.class);

    public EggFrames(int numberOfEggFrames, LinkedList<Integer> eggsByDay) {
        this.numberOfEggFrames = numberOfEggFrames;
        this.eggsByDay = new LinkedList<>(eggsByDay);
    }


    public boolean isWasMovedAnEggsFrame() {
        return wasMovedAnEggsFrame;
    }

    public void setWasMovedAnEggsFrame(boolean wasMovedAnEggsFrame) {
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
    }

    public EggFrames(int numberOfEggFrames, LinkedList<Integer> eggsByDay, boolean wasMovedAnEggsFrame) {
        this.numberOfEggFrames = numberOfEggFrames;
        this.eggsByDay = eggsByDay;
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
    }

    public EggFrames() {
    }

    public EggFrames(int numberOfEggFrames) {
        this.numberOfEggFrames = numberOfEggFrames;
        this.eggsByDay = new LinkedList<>(Collections.nCopies(daysToHatch, 0));
    }


    public static EggFrames getRandomEggFrames() {
        Random random = new Random();
        EggFrames eggFrames = new EggFrames(random.nextInt(3, 5));
        random.ints(daysToHatch, 800, 901).forEach(eggFrames::iterateOneDay);
        return eggFrames;
    }


    public int getNumberOfEggFrames() {
        return numberOfEggFrames;
    }

    public void setNumberOfEggFrames(int numberOfEggFrames) {
        this.numberOfEggFrames = numberOfEggFrames;
    }

    public EggFrames splitEggFrames() {
        LinkedList<Integer> newEggBatches = new LinkedList<>();
        eggsByDay.replaceAll(eggs -> {
            int halfEggs = eggs / 2;
            newEggBatches.add(halfEggs);
            return halfEggs;
        });
        numberOfEggFrames = 3;
        return new EggFrames(3, newEggBatches, false);
    }

    List<Integer> extractEggBatchesForFrame() {
        int currentSum = 0;
        int index = 0;
        while (index < eggsByDay.size() && (currentSum += eggsByDay.get(index)) <= maxEggPerFrame) {
            index++;
        }
        List<Integer> extractedBatches = new ArrayList<>(eggsByDay.subList(0, index));
        eggsByDay.subList(0, index).replaceAll(x -> 0);
        numberOfEggFrames--;
        return extractedBatches;
    }


    public void addEggBatches(List<Integer> batchesToAdd) {
        for (int i = 0; i < batchesToAdd.size(); i++)
            eggsByDay.set(i, eggsByDay.get(i) + batchesToAdd.get(i));
        numberOfEggFrames++;
    }

    public boolean isFull() {
        return getEggs() >= maxEggPerFrame * numberOfEggFrames;
    }


    public int getEggs() {
        return eggsByDay.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean is80PercentFull() {
        return getEggs() >= numberOfEggFrames * maxEggPerFrame * 0.8;
    }

    public int iterateOneDay(int eggsToAdd) {
        eggsByDay.addFirst(Math.min(eggsToAdd, maxEggPerFrame * numberOfEggFrames - getEggs()));
        return eggsByDay.removeLast();
    }

    public void incrementNumberOfEggFrames() {
        if (this.numberOfEggFrames < maxNumberOfEggFrames)
            this.numberOfEggFrames++;
    }

    public boolean isFullEggFrames() {
        return numberOfEggFrames == maxNumberOfEggFrames;
    }


    public boolean checkIfAll6EggsFrameAre80PercentFull() {
        return isFullEggFrames() || !is80PercentFull();
    }

    public boolean canAddNewEggsFrame() {
        return !isFullEggFrames() && is80PercentFull();
    }

    @Override
    public String toString() {
        return "EggFrames{" +
                "numberOfEggFrames=" + numberOfEggFrames +
                ", eggBatches=" + eggsByDay +
                '}';
    }


    public void moveAnEggFrame(EggFrames destinationEggFrame) {
        for (int i = 0; i < daysToHatch; i++) {
            int sourceEggs = eggsByDay.get(i);
            int destinationEggs = destinationEggFrame.eggsByDay.get(i);
            int eggsToMove = sourceEggs / numberOfEggFrames;
            eggsByDay.set(i, sourceEggs - eggsToMove);
            destinationEggFrame.eggsByDay.set(i, destinationEggs + eggsToMove);
        }
        setWasMovedAnEggsFrame(true);
        numberOfEggFrames--;
        destinationEggFrame.numberOfEggFrames++;
    }


    public void hibernate() {
        for (int i = 0; i < daysToHatch; i++) {
            Integer sourceEggs = eggsByDay.get(i);
            int eggsToMove = sourceEggs / numberOfEggFrames;
            eggsByDay.set(i, sourceEggs - eggsToMove);
        }
        numberOfEggFrames--;
    }
}
