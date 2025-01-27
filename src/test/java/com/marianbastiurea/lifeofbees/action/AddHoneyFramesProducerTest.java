package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.bees.HoneyFrames;
import org.junit.jupiter.api.Test;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.maxNumberOfHoneyFrames;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddHoneyFramesProducerTest {
    @Test
    void cantAddHoneyFrameToHiveWithMaxNumberOfHoneyFrames() {
        Optional<List<Integer>> result = new AddHoneyFramesProducer().produce(
                new Hives(new Hive(1, new HoneyFrames(maxNumberOfHoneyFrames, 4)))
        );
        assertTrue(result.isEmpty(), "Should not add honey frame when at max capacity.");
    }

    @Test
    void addHoneyFramesProducerTest_mixedCases() {
        Optional<List<Integer>> result = new AddHoneyFramesProducer().produce(
                new Hives(
                        new Hive(1, new HoneyFrames(maxNumberOfHoneyFrames - 1, 5)),
                        new Hive(2, new HoneyFrames(maxNumberOfHoneyFrames - 1, 3)),
                        new Hive(3, new HoneyFrames(maxNumberOfHoneyFrames, 5))
                )
        );

        assertEquals(Optional.of(List.of(1)), result, "Only hive 1 should be eligible for adding honey frames.");
    }


}