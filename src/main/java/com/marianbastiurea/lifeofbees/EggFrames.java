package com.marianbastiurea.lifeofbees;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    public void setNumberOfEggFrames(int numberOfEggFrames) {
        this.numberOfEggFrames = numberOfEggFrames;
    }

    public LinkedList<Integer> getEggBatches() {
        return eggBatches;
    }

    public int getMaxEggPerFrame() {
        return maxEggPerFrame;
    }

    public EggFrames createEggFrames() {
        Random random = new Random();
        int numberOfEggFrames = random.nextInt(2) + 3;
        LinkedList<Integer> eggBatches = new LinkedList<>();
        for (int j = 0; j < 21; j++) {
            eggBatches.add(random.nextInt(101) + 900);
        }
        return new EggFrames(numberOfEggFrames, eggBatches);
    }


    public EggFrames splitEggFrames(EggFrames oldEggFRames) {

        LinkedList<Integer> newEggBatches = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            newEggBatches.add(oldEggFRames.getEggBatches().removeFirst());
        }
        return new EggFrames(3, newEggBatches);
    }

    public void moveAnEggsFrameFromOneHiveToAnother(Hive sourceHive, Hive destinationHive) {
        List<Integer> eggBatchesToMove;
        final int[] sum = {0};
        eggBatchesToMove = sourceHive.getEggFrames().getEggBatches().stream()
                .takeWhile(batch -> sum[0] + batch <= 6400)
                .peek(batch -> sum[0] += batch)
                .collect(Collectors.toList());
        destinationHive.getEggFrames().getEggBatches().addAll(eggBatchesToMove);
        destinationHive.getEggFrames().setNumberOfEggFrames(destinationHive.getEggFrames().getNumberOfEggFrames() + 1);
        sourceHive.getEggFrames().getEggBatches().removeAll(eggBatchesToMove);
        sourceHive.getEggFrames().setNumberOfEggFrames(sourceHive.getEggFrames().getNumberOfEggFrames() - 1);
    }


    public boolean isFull() {
        int totalEggs = eggBatches.stream().mapToInt(Integer::intValue).sum();
        return totalEggs >= maxEggPerFrame * numberOfEggFrames;
    }

    public int getNumberOf80PercentEggsFrame() {
        int totalEggs = eggBatches.stream().mapToInt(Integer::intValue).sum();

        if (totalEggs >= maxEggPerFrame * numberOfEggFrames * 0.8) {
            return numberOfEggFrames;
        }
        return 0;
    }


    public EggFrames fillUpAnEggFrames(int numberOfEggs, EggFrames eggFrames) {
        if (eggFrames.getNumberOfEggs() + numberOfEggs < eggFrames.getMaxEggPerFrame() * eggFrames.getNumberOfEggFrames()) {
            eggFrames.getEggBatches().addFirst(numberOfEggs);
        } else {
            eggFrames.getEggBatches().addFirst(eggFrames.getMaxEggPerFrame() * eggFrames.getNumberOfEggFrames() - eggFrames.getNumberOfEggs());
        }
        return eggFrames;
    }

    public void removeLastTwoEggBatches(EggFrames eggFrames) {
        eggFrames.getEggBatches().removeLast();
        eggFrames.getEggBatches().removeLast();
    }


    public boolean is80PercentFull() {
        int totalEggs = eggBatches.stream().mapToInt(Integer::intValue).sum();
        return totalEggs >= numberOfEggFrames * maxEggPerFrame * 0.8;
    }

    public int checkAndHatchEggs() {
        return eggBatches.removeLast();
    }

    public int getNumberOfEggs() {
        int totalEggs = 0;
        for (int eggs : eggBatches) {
            totalEggs += eggs;
        }
        return totalEggs;
    }

    @Override
    public String toString() {
        return "EggFrames{" +
                "numberOfEggFrames=" + numberOfEggFrames +
                ", eggBatches=" + eggBatches +
                '}';
    }
}
