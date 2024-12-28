package com.marianbastiurea.lifeofbees.bees;

class HoneyFrame {
    private double maxKgOfHoneyPerFrame = 4.5;
    private double kgOfHoney;

    public HoneyFrame(double kgOfHoney) {
        this.kgOfHoney = kgOfHoney;
    }


    public void fill(double kgOfHoneyToAdd) {
        if (kgOfHoney < maxKgOfHoneyPerFrame) {
            kgOfHoney = Math.min(maxKgOfHoneyPerFrame, kgOfHoney + kgOfHoneyToAdd);
        }
    }

    public boolean isHarvestable() {
        return kgOfHoney > 4;
    }

    public boolean isFull() {
        return kgOfHoney == maxKgOfHoneyPerFrame;
    }

    public double harvest() {
        double harvest = kgOfHoney;
        kgOfHoney = 0;
        return harvest;
    }

    @Override
    public String toString() {
        return "{" +
                "kgOfHoney=" + kgOfHoney +
                '}';
    }
}
