package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.EggFrames;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SplitHiveProducerTest {


    @Test
    void couldNotSplitHiveBecauseNoMaxNumberOfEggsFrame() {
        Hives hives = new Hives(
                List.of(new Hive(1, new EggFrames(5, new LinkedList<>(Arrays.asList(5000, 5000, 5000, 5000, 5000)), false))),
                new BeeTime(2025, 4, 10)
        );
        Optional<List<Integer>> result = new SplitHiveProducer().produce(hives);
        assertTrue(result.isEmpty(), "Expected result to be empty because hive does not have max number of eggs frame.");

    }

    @Test
    void couldNotSplitHiveBecauseWrongDate() {
        Hives hives = new Hives(
                List.of(new Hive(1, new EggFrames(6, new LinkedList<>(Arrays.asList(6400,6400,6400,6400,6400,6400)), false))),
                new BeeTime(2025, 3, 10)
        );
        Optional<List<Integer>> result = new SplitHiveProducer().produce(hives);
        assertTrue(result.isEmpty(), "Expected result to be empty because hive does not have max number of eggs frame.");

    }

    @Test
    void couldNotSplitHiveBecauseWasSplit() {
        Hives hives = new Hives(
                List.of(new Hive(1, new EggFrames(6, new LinkedList<>(Arrays.asList(6400,6400,6400,6400,6400,6400)), false))),
                new BeeTime(2025, 4, 10)
        );
        hives.getHiveById(1).setItWasSplit(true);
        assertFalse(new SplitHiveProducer().produce(hives).isPresent());
    }
    @Test
    void couldNotSplitHiveEggsFrameNotFull() {
        Hives hives = new Hives(
                List.of(new Hive(1, new EggFrames(6, new LinkedList<>(Arrays.asList(5000,5000,5000,5000,5000,5000)), false))),
                new BeeTime(2025, 4, 10)
        );
        Optional<List<Integer>> result = new SplitHiveProducer().produce(hives);
        assertTrue(result.isEmpty(), "Expected result to be empty because hive does not have max number of eggs frame.");

    }

    @Test
    void splitHiveOk() {
        Hives hives = new Hives(
                List.of(new Hive(1, new EggFrames(6, new LinkedList<>(Arrays.asList(6400,6400,6400,6400,6400,6400)), false))),
                new BeeTime(2025, 4, 10)
        );
        Optional<List<Integer>> result = new SplitHiveProducer().produce(hives);
        assertEquals(List.of(1), result.get(), "Expected result to contain the hiveId 1.");
    }


}

