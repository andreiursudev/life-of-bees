package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.EggFrames;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SplitHiveProducerTest {


    @Test
    void couldNotSplitHiveBecauseNoMaxNumberOfEggsFrame() {
        Hives hives = new Hives(
                List.of(new Hive(1, new EggFrames(5, new LinkedList<>(Arrays.asList(5000, 5000, 5000, 5000, 5000)), false))),
                new BeeTime(2025, 4, 10)
        );
        assertFalse(new SplitHiveProducer().produce(hives).isPresent());
    }

    @Test
    void couldNotSplitHiveBecauseWrongDate() {
        Hives hives = new Hives(
                List.of(new Hive(1, new EggFrames(6, new LinkedList<>(Arrays.asList(6400,6400,6400,6400,6400,6400)), false))),
                new BeeTime(2025, 3, 10)
        );
        assertFalse(new SplitHiveProducer().produce(hives).isPresent());
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
        assertFalse(new SplitHiveProducer().produce(hives).isPresent());
    }

    @Test
    void splitHiveOk() {
        Hives hives = new Hives(
                List.of(new Hive(1, new EggFrames(6, new LinkedList<>(Arrays.asList(6400,6400,6400,6400,6400,6400)), false))),
                new BeeTime(2025, 4, 10)
        );
        assertTrue(new SplitHiveProducer().produce(hives).isPresent());
    }


}

