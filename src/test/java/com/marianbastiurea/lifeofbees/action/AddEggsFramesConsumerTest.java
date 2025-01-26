package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.EggFrames;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddEggsFramesConsumerTest {

    @Test
    void addsFramesToSpecifiedHives() {
        AddEggsFramesConsumer consumer = new AddEggsFramesConsumer();
        Hive hive1 = new Hive(1, new EggFrames(2, 0.8));
        Hive hive2 = new Hive(2, new EggFrames(3, 0.8));
        Hive hive3 = new Hive(3, new EggFrames(2, 0.8));

        List<Hive> hiveList = new ArrayList<>(List.of(hive1, hive2, hive3));
        Hives hives = new Hives(hiveList);
        List<Integer> hiveIds = List.of(1, 2);
        consumer.accept(hives, hiveIds);
        assertEquals(3, hive1.getEggFrames().getNumberOfEggFrames());
        assertEquals(4, hive2.getEggFrames().getNumberOfEggFrames());
        assertEquals(2, hive3.getEggFrames().getNumberOfEggFrames()); // No change
    }

    @Test
    void doesNothingWhenEggHiveIdsIsEmpty() {

        AddEggsFramesConsumer consumer = new AddEggsFramesConsumer();

        Hive hive1 = new Hive(1, new EggFrames(2, 0.8));
        Hive hive2 = new Hive(2, new EggFrames(3, 0.8));

        List<Hive> hiveList = new ArrayList<>(List.of(hive1, hive2));
        Hives hives = new Hives(hiveList);
        consumer.accept(hives, List.of());

        assertEquals(2, hive1.getEggFrames().getNumberOfEggFrames());
        assertEquals(3, hive2.getEggFrames().getNumberOfEggFrames());
    }

}