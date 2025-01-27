package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.EggFrames;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeedBeesProducerTest {

    @Test
    void cantFeedBeesIfMonthIsNotSeptember() {
        Optional<Boolean> result = new FeedBeesProducer().produce(
                new Hives(
                        List.of(
                                new Hive(1, new EggFrames(4, 0.1)),
                                new Hive(2, new EggFrames(4, 0.1))
                        ),
                        new BeeTime(2023, 8, 22) // AUGUST
                )
        );
        assertTrue(result.isPresent(), "Result should not be empty.");
        assertFalse(result.get(), "Month is not September, should not feed bees.");
    }


    @Test
    void canFeedBeesIfMonthSeptember() {
        Optional<Boolean> result = new FeedBeesProducer().produce(
                new Hives(
                        List.of(
                                new Hive(1, new EggFrames(4, 0.1)),
                                new Hive(2, new EggFrames(4, 0.1))
                        ),
                        new BeeTime(2023, 9, 22) // September
                )
        );
        assertTrue(result.isPresent(), "Result should not be empty for September.");
        assertTrue(result.get(), "Month is September");
    }
}