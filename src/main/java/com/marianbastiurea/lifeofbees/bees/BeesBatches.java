package com.marianbastiurea.lifeofbees.bees;

import java.util.LinkedList;

public class BeesBatches {

    LinkedList<Integer> beesBatches = new LinkedList<>();



    public void removeLastTwoBeesBatches() {
        logger.debug("Starting removeLastTwoBeesBatches method.");
        for (Hive hive : hives) {
            logger.debug("Processing hive: {}", hive);
            hive.getBeesBatches().removeLast();
            logger.debug("Removed the last batch of bees from hive: {}", hive);
            hive.getBeesBatches().removeLast();
            logger.debug("Removed the second last batch of bees from hive: {}", hive);
        }
        logger.debug("Completed removeLastTwoBeesBatches method.");
    }

}
