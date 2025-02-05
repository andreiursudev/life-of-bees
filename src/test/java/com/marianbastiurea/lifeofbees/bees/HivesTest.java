package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class HivesTest {


    private static final Logger logger = LoggerFactory.getLogger(HivesTest.class);
    @Mock
    private RandomParameters randomParameters;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHibernate() {
        BeeTime initialCurrentDate = new BeeTime(LocalDate.of(2024, 9, 30));
        BeeTime actualCurrentDate = new BeeTime(LocalDate.of(2025, 3, 1));

        List<Hive> initialHiveList = new ArrayList<>(Arrays.asList(new Hive(1, true,
                        new EggFrames(6, 1920, false),
                        new HoneyFrames(
                                Arrays.asList(
                                        new HoneyFrame(4.5),
                                        new HoneyFrame(3.7),
                                        new HoneyFrame(4.1),
                                        new HoneyFrame(4.3),
                                        new HoneyFrame(3.0),
                                        new HoneyFrame(4.0)
                                )
                        ), createBeesBatches(30, 100),
                        new ArrayList<>(List.of(new HoneyBatch(1, 10.0, HoneyType.Acacia, false))), new Queen(2), true),
                new Hive(2, true,
                        new EggFrames(6, 1920, false),
                        new HoneyFrames(
                                Arrays.asList(
                                        new HoneyFrame(1.5),
                                        new HoneyFrame(3.7),
                                        new HoneyFrame(2.1),
                                        new HoneyFrame(3.3),
                                        new HoneyFrame(4.0),
                                        new HoneyFrame(1.0)
                                )
                        ), createBeesBatches(30, 200),
                        new ArrayList<>(List.of(new HoneyBatch(1, 10.0, HoneyType.Acacia, false))), new Queen(2), true)
        ));
        Hives initialHives = new Hives(initialHiveList, initialCurrentDate);


        List<Hive> actualHiveList = List.of(new Hive(1, false, new EggFrames(5, 1600, false),
                new HoneyFrames(
                        Arrays.asList(
                                new HoneyFrame(4.5),
                                new HoneyFrame(3.7),
                                new HoneyFrame(4.1),
                                new HoneyFrame(4.3)
                        )
                ),
                createBeesBatches(30, 70),
                new ArrayList<>(),
                new Queen(3),
                false));

        Hives actualHives = new Hives(actualHiveList, actualCurrentDate);

        when(randomParameters.hiveIdToRemove(initialHives.getHives().size())).thenReturn(2);


        logger.info("this is initialHives before hibernate: {}", initialHives);
        Integer removedHive = initialHives.hibernate();
        System.out.println();
        logger.info("this is initialHives after hibernate:{}", initialHives);

        System.out.println();
        logger.info("this is actualHives: {}", actualHives);


        assertEquals(new BeeTime(2025, 3, 1), initialHives.getCurrentDate());
        assertEquals(2, removedHive);
        assertEquals(initialHives,actualHives);

    }

    private static BeesBatches createBeesBatches(int x, int e) {
        BeesBatches beesBatches = new BeesBatches();
        for (int i = 0; i < x; i++) {
            beesBatches.add(e);
        }
        return beesBatches;
    }

}
