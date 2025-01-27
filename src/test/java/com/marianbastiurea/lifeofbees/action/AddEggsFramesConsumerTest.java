//package com.marianbastiurea.lifeofbees.action;
//
//import com.marianbastiurea.lifeofbees.bees.EggFrames;
//import com.marianbastiurea.lifeofbees.bees.Hive;
//import com.marianbastiurea.lifeofbees.bees.Hives;
//import org.junit.jupiter.api.Test;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class AddEggsFramesConsumerTest {
//
//    @Test
//    void addsFramesToSpecifiedHives() {
//        AddEggsFramesConsumer consumer = new AddEggsFramesConsumer();
//
//        Hives hives = new Hives(
//                new Hive(1, new EggFrames(4, 1)),
//                new Hive(2, new EggFrames(3, 1)),
//                new Hive(3, new EggFrames(2, 1)));
//
//        consumer.accept(hives, List.of(1, 2));
//
//        assertEquals(new Hives(
//                new Hive(1, new EggFrames(5, 0.8)),
//                new Hive(2, new EggFrames(4, 0.75)),
//                new Hive(3, new EggFrames(2, 1))),
//                hives);
//    }
//
//    @Test
//    void doesNothingWhenEggHiveIdsIsEmpty() {
//        AddEggsFramesConsumer consumer = new AddEggsFramesConsumer();
//
//        Hives hives = new Hives(
//                new Hive(1, new EggFrames(2, 0.8)),
//                new Hive(2, new EggFrames(3, 0.8)));
//        consumer.accept(hives, List.of());
//
//        assertEquals(new Hives(
//                new Hive(1, new EggFrames(2, 0.8)),
//                new Hive(2, new EggFrames(3, 0.8))),
//                hives);
//    }
//
//}