package com.marianbastiurea.lifeofbees;

import java.util.*;


public class EggFrames {

    private final int maxEggPerFrame = 6400;

    private int numberOfEggFrames;
    LinkedList<Integer> eggBatches = new LinkedList<>();


    public EggFrames() {
    }

    public EggFrames(int numberOfEggFrames, LinkedList<Integer> eggBatches) {
        this.numberOfEggFrames = numberOfEggFrames;
        this.eggBatches = eggBatches;
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

    public void setEggBatches(LinkedList<Integer> eggBatches) {
        this.eggBatches = eggBatches;
    }

    public boolean isFull() {
        //return sum eggBatches == numberOfEggFrames * maxEggPerFrame
        return true;
    }

    public boolean is80PercentFull() {
        //return sum eggBatches > numberOfEggFrames * maxEggPerFrame * 80%
        return true;
    }

//    public void addEggs(int numberOfEggs) {
//        if (getNumberOfEggs() + numberOfEggs <= maxEggPerFrame) {
//            eggBatches.addFirst(numberOfEggs);
//        }
//    }
//
//    public int checkAndHatchEggs() {
//        return eggBatches.removeLast();
//    }
//
//    public boolean isEggFrameFull() {
//        return getNumberOfEggs() == maxEggPerFrame;
//    }
//
//    public boolean is80PercentFull() {
//        return getNumberOfEggs() <= 0.8 * maxEggPerFrame;
//    }
//}




    @Override
    public String toString() {
        return "EggFrames{" +
                "numberOfEggFrames=" + numberOfEggFrames +
                ", eggBatches=" + eggBatches +
                '}';
    }

    public List<EggFrames> createFirstEggFrames() {
        List<EggFrames> eggFrames = new ArrayList<>();
        Random random = new Random();
        int numberOfEggFrames = random.nextInt(2) + 3;
        for (int i = 0; i < numberOfEggFrames; i++) {
            LinkedList<Integer> eggBatches = new LinkedList<>();
            for (int j = 0; j < 20; j++) {
                eggBatches.add(random.nextInt(151) + 800);
            }
            eggFrames.add(new EggFrames(1, eggBatches));
        }
        return eggFrames;
    }


    public List<EggFrames> removeAnEggFrames(List<EggFrames> eggFrames) {
        int sum = 0;
        while (!eggBatches.isEmpty() && sum < maxEggPerFrame) {
            sum += eggBatches.getFirst();  // Adăugăm primul element la sumă
            eggBatches.removeFirst();
        }
        if (sum >= maxEggPerFrame) {
            numberOfEggFrames--;
        }

        return eggFrames;
    }

    public List<EggFrames> splitEggFrame(List<EggFrames> eggFrames) {
        List<EggFrames> newEggFrames = new ArrayList<>();
        EggFrames splitEggFrames = new EggFrames();
        for (int i = 0; i < 10 && !this.eggBatches.isEmpty(); i++) {
            splitEggFrames.eggBatches.add(this.eggBatches.removeFirst());
        }
        this.numberOfEggFrames = 3;
        splitEggFrames.numberOfEggFrames = 3;
        newEggFrames.add(splitEggFrames);
        return newEggFrames;
    }

    public LinkedList<Integer> moveAnEggFramesToOtherHive(List<EggFrames> eggFrames) {
        LinkedList<Integer> newEggBatches = new LinkedList<>();
        int sum = 0;
        while (!this.eggBatches.isEmpty() && sum < maxEggPerFrame) {
            int batch = this.eggBatches.getFirst();
            sum += batch;
            newEggBatches.add(this.eggBatches.removeFirst());
        }
        this.numberOfEggFrames--;
        return newEggBatches;
    }

}



