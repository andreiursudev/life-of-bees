package com.marianbastiurea.lifeofbees.bees;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.*;
import java.util.Objects;

public class HoneyFrame {


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
        return kgOfHoney > minKgOfHoneyToHarvestAHoneyFrame;
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
        return Double.compare(kgOfHoney, that.kgOfHoney) == 0;
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
