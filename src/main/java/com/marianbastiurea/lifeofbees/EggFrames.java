package com.marianbastiurea.lifeofbees;

import java.util.LinkedList;

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
