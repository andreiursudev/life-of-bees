package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.bees.HoneyFrame;
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
                new Hives(new Hive(1, new HoneyFrames(List.of(
                        new HoneyFrame(1.0), new HoneyFrame(2.0), new HoneyFrame(3.5),
                        new HoneyFrame(4.0), new HoneyFrame(2.5), new HoneyFrame(2.5)
                ))))
        );

        assertTrue(result.isEmpty(), "Should not add honey frame when at max capacity.");
    }



}