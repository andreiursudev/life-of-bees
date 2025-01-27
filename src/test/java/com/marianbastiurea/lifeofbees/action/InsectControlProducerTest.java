package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.EggFrames;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InsectControlProducerTest {
    @Test
    void cantDoInsectControlIfMonthIsMarch() {
        Optional<Boolean> result = new InsectControlProducer().produce(
                new Hives(
                        List.of(
                                new Hive(1, new EggFrames(4, 0.1)),
                                new Hive(2, new EggFrames(4, 0.1))
                        ),
                        new BeeTime(2023, 3, 22)
                )
        );
       assertTrue(result.isEmpty(), "Insect control should not be allowed in March.");
    }

    @Test
    void cantDoInsectControlIfMonthIsAprilAndDayIs1() {
        Optional<Boolean> result = new InsectControlProducer().produce(
                new Hives(
                        List.of(
                                new Hive(1, new EggFrames(4, 0.1)),
                                new Hive(2, new EggFrames(4, 0.1))
                        ),
                        new BeeTime(2023, 4, 1)
                )
        );
        assertTrue(result.isEmpty(), "Insect control should not be allowed in 1st April");
    }

    @Test
    void canDoInsectControlIfMonthIsAprilAndDayIs10() {
        Optional<Boolean> result = new InsectControlProducer().produce(
                new Hives(
                        List.of(
                                new Hive(1, new EggFrames(4, 0.1)),
                                new Hive(2, new EggFrames(4, 0.1))
                        ),
                        new BeeTime(2023, 4, 10)
                )
        );
        assertTrue(result.isPresent(), "Insect control should be allowed on the last valid day.");
        assertTrue(result.get(), "The result should contain true for the last valid day.");

    }

}