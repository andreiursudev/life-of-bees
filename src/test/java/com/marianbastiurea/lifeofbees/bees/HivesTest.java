package com.marianbastiurea.lifeofbees.bees;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HivesTest {

    @Test
    void testSplitHive() {
        EggFrames eggFrames = new EggFrames(6, 600);
        EggFrames halfEggFrames = new EggFrames(3, 300);
        HoneyFrames honeyFrames = new HoneyFrames(4, 3);
        HoneyFrames halfHoneyFrames = new HoneyFrames(2, 3);
        LinkedList<Integer> beesBatches = createBeesBatches(10, 100);
        LinkedList<Integer> halfBeesBatches = createBeesBatches(10, 50);
        Hives hives = new Hives(new Hive(1, false, eggFrames, honeyFrames, beesBatches, new ArrayList<>(), new Queen(1), true));

        hives.splitHive(1);

        Hives actual = new Hives(
                new Hive(1, true, halfEggFrames, halfHoneyFrames, halfBeesBatches, new ArrayList<>(), new Queen(1), true),
                new Hive(2, true, halfEggFrames, halfHoneyFrames, halfBeesBatches, new ArrayList<>(), new Queen(0), false));
        assertEquals(actual, hives);
    }


    private static LinkedList<Integer> createBeesBatches(int x, int e) {
        LinkedList<Integer> beesBatches = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            beesBatches.add(e);
        }
        return beesBatches;
    }

}
