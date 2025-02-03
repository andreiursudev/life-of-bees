package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HivesTest {

    private Hives initialHives;
    private Hives actualHives;
    private BeeTime initialCurrentDate;
    private BeeTime actualCurrentDate;

    @BeforeEach
    void setUpInitialHiveList() {
        initialCurrentDate = new BeeTime(LocalDate.of(2024, 9, 30));

        List<Hive> initialHiveList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Hive hive = new Hive();
            hive.setId(i);
            hive.setQueen(new Queen(2));
            hive.setItWasSplit(true);
            hive.setEggFrames(new EggFrames(6, 1920, false));
            hive.setHoneyFrames(new HoneyFrames(
                    Arrays.asList(
                            new HoneyFrame(4.5),
                            new HoneyFrame(3.7),
                            new HoneyFrame(4.1),
                            new HoneyFrame(4.3),
                            new HoneyFrame(3.0),
                            new HoneyFrame(4.0)
                    )
            ));
            hive.setBeesBatches(createBeesBatches(30, 100));
            hive.setHoneyBatches(new ArrayList<>(List.of(new HoneyBatch(i, 10.0, HoneyType.Acacia, false))));
            initialHiveList.add(hive);
        }

        initialHives = new Hives(initialHiveList, initialCurrentDate);
    }

    void setUpActualHiveList() {
        actualCurrentDate = new BeeTime(LocalDate.of(2025, 3, 1));

        List<Hive> actualHiveList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Hive hive = new Hive();
            hive.setId(i);
            hive.setQueen(new Queen(3));
            hive.setItWasSplit(false);
            hive.setEggFrames(new EggFrames(5, 1600, false));
            hive.setHoneyFrames(new HoneyFrames(
                    Arrays.asList(
                            new HoneyFrame(4.5),
                            new HoneyFrame(3.7),
                            new HoneyFrame(4.1),
                            new HoneyFrame(4.3)
                    )
            ));
            hive.setBeesBatches(createBeesBatches(30, 70));
            hive.setHoneyBatches(new ArrayList<>());
            actualHiveList.add(hive);
        }

        actualHives = new Hives(actualHiveList, actualCurrentDate);
    }

    @Test
    void testHibernate() {


        Integer removedHive = initialHives.hibernate();
        assertNotNull(removedHive);
        assertTrue(removedHive >= 1 && removedHive <= 3);
        assertEquals(new BeeTime(2025, 3, 1), actualCurrentDate);
        compareHivesObjects(initialHives, actualHives, removedHive);

    }


    private static BeesBatches createBeesBatches(int x, int e) {
        BeesBatches beesBatches = new BeesBatches();
        for (int i = 0; i < x; i++) {
            beesBatches.add(e);
        }
        return beesBatches;
    }

    public static boolean compareHivesObjects(Hives initialHives, Hives actualHives, int removedHiveId) {
        List<Hive> hivesInitialList = initialHives.getHivesList();
        List<Hive> hivesActualList = actualHives.getHivesList();

        if (hivesInitialList.size() != hivesActualList.size() + 1) {
            return false;
        }

        int indexActual = 0;
        for (Hive initialHive : hivesInitialList) {
            if (initialHive.getId() == removedHiveId) {
                continue;
            }

            if (indexActual >= hivesActualList.size() || !initialHive.equals(hivesActualList.get(indexActual))) {
                return false;
            }

            indexActual++;
        }

        return true;
    }

}
