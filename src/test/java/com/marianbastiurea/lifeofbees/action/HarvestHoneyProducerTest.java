package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.bees.HoneyFrame;
import com.marianbastiurea.lifeofbees.bees.HoneyFrames;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HarvestHoneyProducerTest {
    @Test
    void cantHarvestHoneyFrameBecauseNoHarvestableHoneyFrames() {

        List<Hive> hiveList = new ArrayList<>();
        hiveList.add(new Hive(1, new HoneyFrames(new ArrayList<>(Arrays.asList(
                new HoneyFrame(2.0), new HoneyFrame(3.5), new HoneyFrame(3.5),
                new HoneyFrame(1.0), new HoneyFrame(3.5), new HoneyFrame(3.5)
        )))));

        Optional<List<Integer>> result = new HarvestHoneyProducer().produce(
                new Hives(hiveList, new BeeTime(2023, 4, 10))
        );

        assertTrue(result.isEmpty(), "Should not harvest honey frame when is not Harvestable.");
    }

    @Test
    void canHarvestIfIsTimeToHarvestAndHoneyFrameIsHarvestable() {
        List<Hive> hiveList = new ArrayList<>();
        hiveList.add(new Hive(1, new HoneyFrames(new ArrayList<>(Arrays.asList(
                new HoneyFrame(4.0), new HoneyFrame(4.5), new HoneyFrame(4.5),
                new HoneyFrame(4.0), new HoneyFrame(4.5), new HoneyFrame(4.5)
        )))));

        Optional<List<Integer>> result = new HarvestHoneyProducer().produce(
                new Hives(hiveList, new BeeTime(2023, 4, 10))
        );

        assertEquals(Optional.of(List.of(1)), result, "harvest honey frame when is Harvestable.");
    }


    @Test
    void cantHarvestIfIsTimeToHarvestAndHoneyFrameIsNotHarvestable() {
        List<Hive> hiveList = new ArrayList<>();
        hiveList.add(new Hive(1, new HoneyFrames(new ArrayList<>(Arrays.asList(
                new HoneyFrame(2.0), new HoneyFrame(3.5), new HoneyFrame(3.5),
                new HoneyFrame(3.0), new HoneyFrame(3.5), new HoneyFrame(2.5)
        )))));

        Optional<List<Integer>> result = new HarvestHoneyProducer().produce(
                new Hives(hiveList, new BeeTime(2023, 4, 10))
        );

        assertTrue(result.isEmpty(), "harvest honey frame when is Harvestable.");
    }

    @Test
    void cantHarvestIfIsNotTimeToHarvestAndHoneyFrameIsHarvestable() {
        List<Hive> hiveList = new ArrayList<>();
        hiveList.add(new Hive(1, new HoneyFrames(new ArrayList<>(Arrays.asList(
                new HoneyFrame(4.0), new HoneyFrame(4.5), new HoneyFrame(4.5),
                new HoneyFrame(4.0), new HoneyFrame(4.5), new HoneyFrame(4.5)
        )))));

        Optional<List<Integer>> result = new HarvestHoneyProducer().produce(
                new Hives(hiveList, new BeeTime(2023, 4, 1))
        );

        assertTrue(result.isEmpty(), "harvest honey frame when is Harvestable.");
    }


}