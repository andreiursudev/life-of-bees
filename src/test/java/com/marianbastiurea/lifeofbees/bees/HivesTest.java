package com.marianbastiurea.lifeofbees.bees;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HivesTest {

    private static final Logger logger = LoggerFactory.getLogger(HivesTest.class);
//    @Test
//    void testSplitHive() {
//        EggFrames eggFrames = new EggFrames(6, 600);
//        EggFrames halfEggFrames = new EggFrames(3, 300);
//        HoneyFrames honeyFrames = new HoneyFrames(4, 3);
//        HoneyFrames halfHoneyFrames = new HoneyFrames(2, 3);
//        BeesBatches beesBatches = createBeesBatches(10, 100);
//        BeesBatches halfBeesBatches = createBeesBatches(10, 50);
//        Hives hives = new Hives(new Hive(1, false, eggFrames, honeyFrames, beesBatches, new ArrayList<>(), new Queen(1), true));
//
//        hives.splitHive(1);
//
//        Hives actual = new Hives(
//                new Hive(1, true, halfEggFrames, halfHoneyFrames, halfBeesBatches, new ArrayList<>(), new Queen(1), true),
//                new Hive(2, true, halfEggFrames, halfHoneyFrames, halfBeesBatches, new ArrayList<>(), new Queen(0), false));
//        assertEquals(actual, hives);
//    }
@Test
void testSplitHive() {
    logger.info("Starting testSplitHive method.");

    // Inițializarea obiectelor pentru test
    EggFrames eggFrames = new EggFrames(6, 600);
    EggFrames halfEggFrames = new EggFrames(3, 600);
    HoneyFrames honeyFrames = new HoneyFrames(4, 3);
    HoneyFrames halfHoneyFrames = new HoneyFrames(2, 3);
    BeesBatches beesBatches = createBeesBatches(10, 100);
    BeesBatches halfBeesBatches = createBeesBatches(10, 50);

    // Crearea unui Hive inițial
    Hives hives = new Hives(new Hive(1, false, eggFrames, honeyFrames, beesBatches, new ArrayList<>(), new Queen(1), true));
    logger.info("Hive 1 created with eggFrames: {}, honeyFrames: {}, beesBatches: {}", eggFrames, honeyFrames, beesBatches);

    // Apelarea metodei splitHive
    logger.info("Calling splitHive on Hive 1.");
    hives.splitHive(1);

    // Crearea așteptată a hives
    Hives actual = new Hives(
            new Hive(1, true, halfEggFrames, halfHoneyFrames, halfBeesBatches, new ArrayList<>(), new Queen(1), true),
            new Hive(2, true, halfEggFrames, halfHoneyFrames, halfBeesBatches, new ArrayList<>(), new Queen(0), false));
    logger.info("Expected Hives created: {}", actual);

    // Verificarea corectitudinii rezultatului
    logger.info("Asserting the expected result.");
    assertEquals(actual, hives);

    logger.info("Finished testSplitHive method.");
}



    private static BeesBatches createBeesBatches(int x, int e) {
        BeesBatches beesBatches = new BeesBatches();
        for (int i = 0; i < x; i++) {
            beesBatches.add(e);
        }
        return beesBatches;
    }

}