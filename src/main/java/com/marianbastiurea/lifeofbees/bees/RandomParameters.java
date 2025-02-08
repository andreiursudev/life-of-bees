package com.marianbastiurea.lifeofbees.bees;

import java.util.Random;

public class RandomParameters {
    Random random = new Random();

    public RandomParameters() {
    }

    public double chanceToChangeQueen() {
        return random.nextDouble();
    }

    public int numberOfFlights() {
        return random.nextInt(3, 6);
    }

    public int hiveIndexToRemove(int hivesSize) {
        return hivesSize > 0 ? random.nextInt(hivesSize) : 0;
    }

    public int numberOfHoneyFrames() {
        return random.nextInt(3, 5);
    }

    public int numberOfEggFrames() {
        return random.nextInt(3, 5);
    }

    public int numberOfBees(){
        return random.nextInt(600, 700);
    }

    public int ageOfQueen(){
        return random.nextInt(1, 6);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 1;

    }
}
