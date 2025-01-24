package com.marianbastiurea.lifeofbees.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class BeesBatches {

    LinkedList<Integer> beesBatches = new LinkedList<>();
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

    @Override
    public String toString() {
        return "BeesBatches{" +
                "beesBatches=" + beesBatches +
                '}';
    }

    public void removeLastTwoBeesBatches(Hives hives) {
        logger.debug("Starting removeLastTwoBeesBatches method.");
        for (Hive hive : hives.getHives()) {
            LinkedList<Integer> beesBatches = hive.getBeesBatches();
            if (beesBatches != null && beesBatches.size() >= 2) {
                beesBatches.removeLast();
                 beesBatches.removeLast(); // Eliminăm penultimul lot
            } else {
                logger.warn("Hive {} does not have enough bee batches to remove.", hive);
            }
        }
        logger.debug("Completed removeLastTwoBeesBatches method.");
    }
}


