package com.marianbastiurea.lifeofbees;

import java.util.*;

public class EggFrame {

    private final int maxEggPerFrame = 6400;

    LinkedList<Integer> eggBatches = new LinkedList<>();

    public EggFrame(LinkedList<Integer> eggBatches) {
        this.eggBatches = eggBatches;
    }

    public LinkedList<Integer> getEggBatches() {
        return eggBatches;
    }

    public void setEggBatches(LinkedList<Integer> eggBatches) {
        this.eggBatches = eggBatches;
    }

    public int getNumberOfEggs() {
        return eggBatches.stream().mapToInt(Integer::intValue).sum();
    }

    public EggFrame() {
    }

    public int getMaxEggPerFrame() {
        return maxEggPerFrame;
    }

    @Override
    public String toString() {
        return "EggsFrame{" +
                "numberOfEggs=" + getNumberOfEggs() +
                " , eggsBatches=" + eggBatches +
                '}' + "\n";
    }

    public void addEggs(int numberOfEggs) {
        if (getNumberOfEggs() + numberOfEggs <= maxEggPerFrame) {
            eggBatches.addFirst(numberOfEggs);
        }
    }

    public int checkAndHatchEggs() {
        return eggBatches.removeLast();
    }

    public boolean isEggFrameFull() {
        return getNumberOfEggs() == maxEggPerFrame;
    }

    public boolean is80PercentFull() {
        return getNumberOfEggs() <= 0.8 * maxEggPerFrame;
    }
}



