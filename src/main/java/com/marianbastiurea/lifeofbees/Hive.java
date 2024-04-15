package com.marianbastiurea.lifeofbees;

public class Hive {
    private double honeyFrames;
    private double eggsFrames;
    public Hive(double honeyFrames, double eggsFrames) {
        this.honeyFrames = honeyFrames;
        this.eggsFrames = eggsFrames;
    }

    public double getEggsFrames() {
        return eggsFrames;
    }

    public double getHoneyFrames() {
        return honeyFrames;
    }

    public void addEggFrames(double eggs) {
        eggsFrames+=eggs;
    }
}
