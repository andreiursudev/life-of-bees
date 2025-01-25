package com.marianbastiurea.lifeofbees.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class BeesBatches {

    LinkedList<Integer> beesBatches;
    private static final Logger logger = LoggerFactory.getLogger(Apiary.class);

    public BeesBatches(LinkedList<Integer> beesBatches) {
        this.beesBatches = beesBatches;
    }

    public LinkedList<Integer> getBeesBatches() {
        return beesBatches;
    }

    public void setBeesBatches(LinkedList<Integer> beesBatches) {
        this.beesBatches = beesBatches;
    }

    public BeesBatches() {
    }

    @Override
    public String toString() {
        return "BeesBatches{" +
                "beesBatches=" + beesBatches +
                '}';
    }

    public void removeLastTwoBeesBatches() {
        logger.debug("Starting removeLastTwoBeesBatches method.");
            if (beesBatches != null && beesBatches.size() >= 2) {
                beesBatches.removeLast();
                beesBatches.removeLast();
            }
        logger.debug("Completed removeLastTwoBeesBatches method.");
    }

    public BeesBatches splitBeesBatches() {
        LinkedList<Integer> newHiveBeesBatches = new LinkedList<>();
        for (int i = 0; i < beesBatches.size(); i++) {
            int bees = beesBatches.get(i);
            int beesToTransfer = bees / 2;
            beesBatches.set(i, bees - beesToTransfer);
            newHiveBeesBatches.add(beesToTransfer);
        }
        return new BeesBatches(newHiveBeesBatches); // Returnează un obiect BeesBatches
    }

    public void removeBeesBatches() {
        getBeesBatches().removeLast();
        getBeesBatches().removeLast();
    }
}


