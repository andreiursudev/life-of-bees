package com.marianbastiurea.lifeofbees.bees;

import java.util.Random;

public class RandomParameters {
    Random random = new Random();
    private Double fixedChance=null;

    public RandomParameters() {}

    public RandomParameters(double fixedChance) { // Constructor pentru teste
        this.fixedChance = fixedChance;
    }

    public double chanceToChangeQueen() {
        return (fixedChance != null) ? fixedChance : random.nextDouble();
    }
    public int numberOfFlights() {
        return random.nextInt(3, 6);
    }

    public int hiveIdToRemove(int hivesSize) {
        return hivesSize > 0 ? random.nextInt(hivesSize) : 0;
    }

    public  int numberOfHoneyFrames(){
        return random.nextInt(3, 5);
    }
    public  int numberOfEggFrames(){
        return random.nextInt(3, 5);
    }

}
