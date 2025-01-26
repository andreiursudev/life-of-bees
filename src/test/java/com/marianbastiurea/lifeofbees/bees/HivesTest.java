package com.marianbastiurea.lifeofbees.bees;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class HivesTest {

    private static final Logger logger = LoggerFactory.getLogger(HivesTest.class);

@Test
void testSplitHive() {
    logger.info("Starting testSplitHive method.");

    EggFrames eggFrames = new EggFrames(6,20);
    EggFrames halfEggFrames = new EggFrames(3, 10);
    HoneyFrames honeyFrames = new HoneyFrames(4, 3);
    HoneyFrames halfHoneyFrames = new HoneyFrames(2, 3);
    BeesBatches beesBatches = createBeesBatches(10, 100);
    BeesBatches halfBeesBatches = createBeesBatches(10, 50);
    Hives hives = new Hives(new Hive(1, false, eggFrames, honeyFrames, beesBatches, new ArrayList<>(), new Queen(1), true));

    hives.splitHive(1);

    Hives actual = new Hives(
            new Hive(1, true, halfEggFrames, halfHoneyFrames, halfBeesBatches, new ArrayList<>(), new Queen(1), true),
            new Hive(2, true, halfEggFrames, halfHoneyFrames, halfBeesBatches, new ArrayList<>(), new Queen(0), false));

    assertEquals(actual, hives);
}

    private static BeesBatches createBeesBatches(int x, int e) {
        BeesBatches beesBatches = new BeesBatches();
        for (int i = 0; i < x; i++) {
            beesBatches.add(e);
        }
        return beesBatches;
    }
}