package com.marianbastiurea.lifeofbees.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.*;

public class BeesBatches {

    private static final Logger logger = LoggerFactory.getLogger(BeesBatches.class);
    LinkedList<Integer> beesBatches;

    public BeesBatches() {
        this.beesBatches = new LinkedList<>();
    }

    public BeesBatches(LinkedList<Integer> beesBatches) {
        this.beesBatches = beesBatches;
    }

    public BeesBatches(int beesPerBatch) {
        beesBatches = new LinkedList<>(Collections.nCopies(daysToLiveForABee, beesPerBatch));
    }

    public LinkedList<Integer> getBeesBatches() {
        return beesBatches;
    }

    public void setBeesBatches(LinkedList<Integer> beesBatches) {
        this.beesBatches = beesBatches;
    }


    @Override
    public String toString() {
        return "BeesBatches{" +
                "beesBatches=" + beesBatches +
                '}';
    }

    public void hibernateBeesBatches() {
        logger.debug("Starting hibernateBeesBatches method.");
        if (beesBatches != null && beesBatches.size() >= 2) {
            for (int i = 0; i < daysToLiveForABee; i++) {
                int beesDiedDueHibernate = (int) (beesBatches.get(i) * percentOfBeesDiedDueToHibernate);
                int newBeeCount = beesBatches.get(i) - beesDiedDueHibernate;
                beesBatches.set(i, Math.max(newBeeCount, 0));
            }
        }
        logger.debug("Completed hibernateBeesBatches method.");
    }


    public BeesBatches splitBeesBatches() {
        LinkedList<Integer> newHiveBeesBatches = new LinkedList<>();
        for (int i = 0; i < beesBatches.size(); i++) {
            int bees = beesBatches.get(i);
            int beesToTransfer = bees / 2;
            beesBatches.set(i, bees - beesToTransfer);
            newHiveBeesBatches.add(beesToTransfer);
        }
        return new BeesBatches(newHiveBeesBatches);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeesBatches that = (BeesBatches) o;
        return Objects.equals(beesBatches, that.beesBatches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beesBatches);
    }

    public void add(int e) {
        this.beesBatches.add(e);
    }

    public double makeHoney(double productivity, int bees, int numberOfFlight) {
        beesBatches.add(bees);
        double totalBeesBatches = beesBatches.stream()
                .mapToInt(Integer::intValue)
                .sum();
        beesBatches.removeFirst();
        return totalBeesBatches * numberOfFlight * pollenQuantityCarriedByABee * productivity;
    }
}


