package com.marianbastiurea.lifeofbees.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.*;

public class EggFrames {

    private int numberOfEggFrames;
    public LinkedList<Integer> eggsByDay;
    public boolean wasMovedAnEggsFrame;
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

    /*
    Use this constructor for tests only.
     */
    public EggFrames(int numberOfEggFrames, double eggFramesFillPercentage) {
        this.numberOfEggFrames = numberOfEggFrames;
        double o = numberOfEggFrames * maxEggPerFrame * eggFramesFillPercentage / daysToHatch;
        this.eggsByDay = new LinkedList<>(Collections.nCopies(daysToHatch, (int) o));
    }


    public static EggFrames getRandomEggFrames() {
        Random random = new Random();
        EggFrames eggFrames = new EggFrames(random.nextInt(3, 5));
        random.ints(daysToHatch, 800, 901).forEach(eggFrames::iterateOneDay);
        logger.debug("Finishing method getRandomEggFrames");
        return eggFrames;
    }


    public int getNumberOfEggFrames() {
        return numberOfEggFrames;
    }

    public void setNumberOfEggFrames(int numberOfEggFrames) {
        this.numberOfEggFrames = numberOfEggFrames;
    }

//    public EggFrames splitEggFrames() {
//        logger.debug("Starting method splitEggFrames with eggsByDay: {}",eggsByDay);
//        LinkedList<Integer> newEggBatches = new LinkedList<>();
//        eggsByDay.replaceAll(eggs -> {
//            int halfEggs = eggs / 2;
//            newEggBatches.add(halfEggs);
//            return halfEggs;
//        });
//        numberOfEggFrames = 3;
//        logger.debug("finishing method splitEggFrames");
//        return new EggFrames(3, newEggBatches, false);
//    }

    public EggFrames splitEggFrames() {
        logger.debug("Starting method splitEggFrames with eggsByDay: {}", eggsByDay);

        // Creăm două liste pentru cele două rame de ouă
        LinkedList<Integer> newEggBatches1 = new LinkedList<>();
        LinkedList<Integer> newEggBatches2 = new LinkedList<>();

        // Log pentru începerea împărțirii
        logger.info("Splitting eggs into two batches.");

        // Împărțim ouăle la jumătate pentru fiecare zi
        for (Integer eggs : eggsByDay) {
            int halfEggs = eggs / 2;
            newEggBatches1.add(halfEggs);
            newEggBatches2.add(eggs - halfEggs); // A doua jumătate

            // Log fiecare împărțire a ouălor
            logger.info("Eggs for the day split: {} -> Batch 1: {}, Batch 2: {}", eggs, halfEggs, eggs - halfEggs);
        }

        numberOfEggFrames = 3; // Poți ajusta acest număr dacă este necesar


        // Creăm și returnăm o nouă instanță de EggFrames cu noile liste
        EggFrames newEggFrames = new EggFrames(3, newEggBatches1, false);

        // Log pentru returnarea noilor egg frames
        logger.info("Returning new EggFrames with {} egg batches.", newEggBatches1.size());

        logger.info("noile rame splituite sunt {}", newEggFrames);
        logger.debug("Finishing method splitEggFrames");
        return newEggFrames;
    }


    public void extractEggBatchesForFrame() {
        logger.debug("starting extractEggBatchesForFrame with eggsByDay: {}",eggsByDay);
        int currentSum = 0;
        int index = 0;
        while (index < eggsByDay.size() && (currentSum += eggsByDay.get(index)) <= maxEggPerFrame) {
            index++;
        }
        eggsByDay.subList(0, index).replaceAll(x -> 0);
        numberOfEggFrames--;
        logger.debug("Finishing method extractEggBatchesForFrame");
    }




    public void addEggBatches(List<Integer> batchesToAdd) {
        for (int i = 0; i < batchesToAdd.size(); i++)
            eggsByDay.set(i, eggsByDay.get(i) + batchesToAdd.get(i));
        numberOfEggFrames++;
    }


    public int getEggs() {
        return eggsByDay.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isFull() {
        return getEggs() >= numberOfEggFrames * maxEggPerFrame * fullnessFactor;
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
        return isFullEggFrames() || !isFull();
    }

    public boolean canAddNewEggsFrame() {
        return !isFullEggFrames() && isFull();
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
