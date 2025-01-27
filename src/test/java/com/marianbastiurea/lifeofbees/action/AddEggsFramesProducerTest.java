package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.fullnessFactor;
import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.maxNumberOfEggFrames;
import static org.junit.jupiter.api.Assertions.*;

class AddEggsFramesProducerTest {

    @Test
    void cantAddEggFrameToHiveWithMaxNumberOfEggFramesAndFullnessOverFullnessFactor() {
        AddEggsFramesProducer addEggsFramesProducer = new AddEggsFramesProducer();
        Hives hives = new Hives(new Hive(1, new EggFrames(maxNumberOfEggFrames, fullnessFactor + 0.1)));


        Optional<List<Integer>> result = addEggsFramesProducer.produce(hives);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void addEggsFramesProducer_mixed_cases() {
        AddEggsFramesProducer addEggsFramesProducer = new AddEggsFramesProducer();
        Hives hives = new Hives(
                new Hive(1, new EggFrames(maxNumberOfEggFrames, fullnessFactor + 0.1)),
                new Hive(2, new EggFrames(maxNumberOfEggFrames, fullnessFactor - 0.1)),
                new Hive(3, new EggFrames(maxNumberOfEggFrames - 1, fullnessFactor)),
                new Hive(4, new EggFrames(maxNumberOfEggFrames - 2, fullnessFactor + 0.1)),
                new Hive(5, new EggFrames(maxNumberOfEggFrames - 1, fullnessFactor - 0.1)
                ));

        Optional<List<Integer>> result = addEggsFramesProducer.produce(hives);

        assertEquals(Optional.of(List.of(3, 4)), result);
    }
}