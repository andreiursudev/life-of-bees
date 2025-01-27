package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.EggFrames;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeedBeesProducerTest {

    @Test
    void cantFeedBeesIfMonthIsNotSeptember() {
        Optional<Integer> result = new FeedBeesProducer().produce(
                new Hives(
                        List.of(
                                new Hive(1, new EggFrames(4, 0.1)),
                                new Hive(2, new EggFrames(4, 0.1))
                        ),
                        new BeeTime(2023, 8, 22)
                )
        );
        assertTrue(result.isEmpty(), "Month is not September");

    }

    @Test
    void canFeedBeesIfMonthSeptember() {
        Optional<Integer> result = new FeedBeesProducer().produce(
                new Hives(
                        List.of(
                                new Hive(1, new EggFrames(4, 0.1)),
                                new Hive(2, new EggFrames(4, 0.1))
                        ),
                        new BeeTime(2023, 9, 22)
                )
        );
        assertTrue(result.isPresent(), "Result should not be empty for September.");
        assertEquals(2, result.get(), "Month is September");
    }
}