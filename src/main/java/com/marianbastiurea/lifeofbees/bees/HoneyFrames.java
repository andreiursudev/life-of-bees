package com.marianbastiurea.lifeofbees.bees;

import java.util.LinkedList;

public class HoneyFrames {
    private int numberOfHoneyFrames;
    private int kgOfHoney;
    private HoneyFrame honeyFrame;

    public HoneyFrames() {
    }

    public HoneyFrames(int numberOfHoneyFrames, int kgOfHoney) {
        this.numberOfHoneyFrames = numberOfHoneyFrames;
        this.kgOfHoney = kgOfHoney;
    }

    public HoneyFrames(int numberOfHoneyFrames, HoneyFrame honeyFrame) {
        this.numberOfHoneyFrames = numberOfHoneyFrames;
        this.honeyFrame = honeyFrame;
    }

    public int getNumberOfHoneyFrames() {
        return numberOfHoneyFrames;
    }

    public void setNumberOfHoneyFrames(int numberOfHoneyFrames) {
        this.numberOfHoneyFrames = numberOfHoneyFrames;
    }

    public int getKgOfHoney() {
        return kgOfHoney;
    }

    public void setKgOfHoney(int kgOfHoney) {
        this.kgOfHoney = kgOfHoney;
    }

    public HoneyFrame getHoneyFrame() {
        return honeyFrame;
    }

    public void setHoneyFrame(HoneyFrame honeyFrame) {
        this.honeyFrame = honeyFrame;
    }

    @Override
    public String toString() {
        return "HoneyFrames{" +
                "numberOfHoneyFrames=" + numberOfHoneyFrames +
                ", kgOfHoney=" + kgOfHoney +
                '}';
    }
}


