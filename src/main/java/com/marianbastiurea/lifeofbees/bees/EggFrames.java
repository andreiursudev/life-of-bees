package com.marianbastiurea.lifeofbees.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.*;

public class EggFrames {

    private int numberOfEggFrames;
    public LinkedList<Integer> eggsByDay;
    public boolean wasMovedAnEggsFrame;
    private static final Logger logger = LoggerFactory.getLogger(EggFrames.class);
    public static RandomParameters randomParameters;

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

    /*
    Use this constructor for tests only.
     */
    public EggFrames(int numberOfEggFrames, int dailyEggs, boolean wasMovedAnEggsFrame) {
        this.numberOfEggFrames = numberOfEggFrames;
        this.eggsByDay = new LinkedList<>(Collections.nCopies(daysToHatch, dailyEggs));
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
    }


    public EggFrames(int numberOfEggFrames, double eggFramesFillPercentage) {
        this.numberOfEggFrames = numberOfEggFrames;
        double o = numberOfEggFrames * maxEggPerFrame * eggFramesFillPercentage / daysToHatch;
        this.eggsByDay = new LinkedList<>(Collections.nCopies(daysToHatch, (int) o));
    }


    public static EggFrames getRandomEggFrames() {
        Random random = new Random();
        EggFrames eggFrames = new EggFrames(randomParameters.numberOfEggFrames());
        random.ints(daysToHatch, 800, 901).forEach(eggFrames::hatchBees);
        logger.debug("Finishing method getRandomEggFrames");
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
        for (Integer eggs : eggsByDay) {
            int halfEggs = eggs / 2;
            newEggBatches.add(halfEggs);
        }
        numberOfEggFrames = 3;
        EggFrames newEggFrames = new EggFrames(3, newEggBatches, false);
        logger.debug("Finishing method splitEggFrames");
        return newEggFrames;
    }


    public void addEggBatches(List<Integer> batchesToAdd) {
        for (int i = 0; i < batchesToAdd.size(); i++)
            eggsByDay.set(i, eggsByDay.get(i) + batchesToAdd.get(i));
        numberOfEggFrames++;
    }


    public int getEggs() {
        return eggsByDay.stream().mapToInt(Integer::intValue).sum();
    }


    public int hatchBees(int eggsToAdd) {
        eggsByDay.addFirst(Math.min(eggsToAdd, maxEggPerFrame * numberOfEggFrames - getEggs()));
        return eggsByDay.removeLast();
    }

    public void incrementNumberOfEggFrames() {
        if (this.numberOfEggFrames < maxNumberOfEggFrames)
            this.numberOfEggFrames++;
    }

    public boolean isMaxNumberOfEggFrames() {
        return numberOfEggFrames == maxNumberOfEggFrames;
    }


    public boolean canAddNewEggsFrame() {
        return !isMaxNumberOfEggFrames() && isFull();
    }

    public boolean checkIfAll6EggsFrameAre80PercentFull() {
        return isMaxNumberOfEggFrames() || isFull();
    }

    public boolean isFull() {
        logger.info("Checking if egg frames are full...");
        int eggs = getEggs();
        boolean result = eggs >= numberOfEggFrames * maxEggPerFrame * fullnessFactor;
        logger.info("Eggs in frames: {}, Fullness check result: {}", eggs, result);
        return result;
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


    public void hibernateEggFrames() {
        for (int i = 0; i < daysToHatch; i++) {
            Integer sourceEggs = eggsByDay.get(i);
            int eggsToMove = sourceEggs / numberOfEggFrames;
            eggsByDay.set(i, sourceEggs - eggsToMove);
        }
        numberOfEggFrames--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EggFrames eggFrames = (EggFrames) o;
        return numberOfEggFrames == eggFrames.numberOfEggFrames && wasMovedAnEggsFrame == eggFrames.wasMovedAnEggsFrame && Objects.equals(eggsByDay, eggFrames.eggsByDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfEggFrames, eggsByDay, wasMovedAnEggsFrame, maxNumberOfEggFrames);
    }

    @Override
    public String toString() {
        return "EggFrames{" +
                "numberOfEggFrames=" + numberOfEggFrames +
                ", eggsByDay=" + eggsByDay +
                ", wasMovedAnEggsFrame=" + wasMovedAnEggsFrame +
                ", maxNumberOfEggFrames=" + maxNumberOfEggFrames +
                '}';
    }
}
