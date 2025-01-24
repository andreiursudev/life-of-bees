package com.marianbastiurea.lifeofbees.bees;


import org.springframework.data.annotation.Transient;

import java.util.Objects;

class HoneyFrame {

    @Transient
    private final double maxKgOfHoneyPerFrame = 4.5;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoneyFrame that = (HoneyFrame) o;
        return Double.compare(maxKgOfHoneyPerFrame, that.maxKgOfHoneyPerFrame) == 0 && Double.compare(kgOfHoney, that.kgOfHoney) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxKgOfHoneyPerFrame, kgOfHoney);
    }

    @Override
    public String toString() {
        return "HoneyFrame{" +
                "maxKgOfHoneyPerFrame=" + maxKgOfHoneyPerFrame +
                ", kgOfHoney=" + kgOfHoney +
                '}';
    }
}
