package com.marianbastiurea.lifeofbees.bees;

import java.util.Random;

public class RandomParameters {
    Random random = new Random();

    public RandomParameters() {}

    public double chanceToChangeQueen() {
        return random.nextDouble();
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
