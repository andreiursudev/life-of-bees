package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.EggFrames;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.bees.MoveEggFramePairHives;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveAnEggsFrameProducerTest {

    @Test
    void testProduceWithValidSourceAndTargetHives() {
        EggFrames sourceEggFrames = new EggFrames(6, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400, 6400)), false);
        Hive sourceHive = new Hive(1, sourceEggFrames, false);
        EggFrames targetEggFrames = new EggFrames(5, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400)), false);
        Hive targetHive = new Hive(2, targetEggFrames, true);
        List<Hive> hives = Arrays.asList(sourceHive, targetHive);
        Hives hiveCollection = new Hives(hives);
        MoveAnEggsFrameProducer producer = new MoveAnEggsFrameProducer();
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection);
        Optional<List<MoveEggFramePairHives>> expected = Optional.of(
                List.of(new MoveEggFramePairHives(1, 2))
        );
        assertEquals(expected, result, "List contains a valid pair");
    }


    @Test
    void testProduceWithNoValidSourceHiveBecauseNumberOfEggsFrameIs5() {
        EggFrames sourceEggFrames = new EggFrames(5, new LinkedList<>(Arrays.asList(5000, 5000, 5000, 5000, 5000)), false);
        Hive sourceHive = new Hive(1, sourceEggFrames, false);
        EggFrames targetEggFrames = new EggFrames(5, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400)), false);
        Hive targetHive = new Hive(2, targetEggFrames, true);
        List<Hive> hives = Arrays.asList(sourceHive, targetHive);
        Hives hiveCollection = new Hives(hives);
        MoveAnEggsFrameProducer producer = new MoveAnEggsFrameProducer();
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection);
        assertEquals(Optional.empty(), result, "No valid answer");
    }


    @Test
    void testProduceWithNoValidSourceHiveBecauseWasAlreadyMovedAnEggsFrame() {
        EggFrames sourceEggFrames = new EggFrames(6, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400, 6400)), true);
        Hive sourceHive = new Hive(1, sourceEggFrames, false);
        EggFrames targetEggFrames = new EggFrames(5, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400)), false);
        Hive targetHive = new Hive(2, targetEggFrames, true);
        List<Hive> hives = Arrays.asList(sourceHive, targetHive);
        Hives hiveCollection = new Hives(hives);
        MoveAnEggsFrameProducer producer = new MoveAnEggsFrameProducer();
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection);
        assertEquals(Optional.empty(), result, "Nu valid answer");
    }


    @Test
    void testProduceWithNoValidSourceHiveBecauseWasAlreadySplit() {

        EggFrames sourceEggFrames = new EggFrames(6, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400, 6400)), false);
        Hive sourceHive = new Hive(1, sourceEggFrames, true);
        EggFrames targetEggFrames = new EggFrames(5, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400)), false);
        Hive targetHive = new Hive(2, targetEggFrames, true);
        List<Hive> hives = Arrays.asList(sourceHive, targetHive);
        Hives hiveCollection = new Hives(hives);
        MoveAnEggsFrameProducer producer = new MoveAnEggsFrameProducer();
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection);
        assertEquals(Optional.empty(), result, "Nu valid answer");
    }

    @Test
    void testProduceWithNoValidTargetHiveBecauseHaveMaxNumberOfEggsFrames() {
        EggFrames sourceEggFrames = new EggFrames(6, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400, 6400)), false);
        Hive sourceHive = new Hive(1, sourceEggFrames, false);
        EggFrames targetEggFrames = new EggFrames(6, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400, 6400)), false);
        Hive targetHive = new Hive(2, targetEggFrames, true);
        List<Hive> hives = Arrays.asList(sourceHive, targetHive);
        Hives hiveCollection = new Hives(hives);
        MoveAnEggsFrameProducer producer = new MoveAnEggsFrameProducer();
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection);
        assertEquals(Optional.empty(), result, "No valid answer");
    }


    @Test
    void testProduceWithNoValidTargetHiveBecauseItNoSplit() {
        EggFrames sourceEggFrames = new EggFrames(6, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400, 6400)), false);
        Hive sourceHive = new Hive(1, sourceEggFrames, false);
        EggFrames targetEggFrames = new EggFrames(5, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400)), false);
        Hive targetHive = new Hive(2, targetEggFrames, false);
        List<Hive> hives = Arrays.asList(sourceHive, targetHive);
        Hives hiveCollection = new Hives(hives);
        MoveAnEggsFrameProducer producer = new MoveAnEggsFrameProducer();
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection);
        assertEquals(Optional.empty(), result, "No valid answer");
    }

    @Test
    void testProduceWithValidSourceAndTwoTargetHives() {

        EggFrames sourceEggFrames = new EggFrames(6, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400, 6400)), false);
        Hive sourceHive = new Hive(1, sourceEggFrames, false);
        EggFrames targetEggFrames1 = new EggFrames(5, new LinkedList<>(Arrays.asList(6400, 6400, 6400, 6400, 6400)), false);
        Hive targetHive1 = new Hive(2, targetEggFrames1, true);
        EggFrames targetEggFrames2 = new EggFrames(3, new LinkedList<>(Arrays.asList(6400, 6400, 6400)), false);
        Hive targetHive2 = new Hive(3, targetEggFrames2, true);
        List<Hive> hives = Arrays.asList(sourceHive, targetHive1, targetHive2);
        Hives hiveCollection = new Hives(hives);
        MoveAnEggsFrameProducer producer = new MoveAnEggsFrameProducer();
        Optional<List<MoveEggFramePairHives>> optionalResult = producer.produce(hiveCollection);
        List<MoveEggFramePairHives> result = optionalResult.get();
        assertEquals(2, result.size(), "Are two valid pairs");
        assertTrue(result.contains(new MoveEggFramePairHives(1, 2)), "Pair hiveId 1 with hiveId2");
        assertTrue(result.contains(new MoveEggFramePairHives(1, 3)), "Pair hiveId1 with hiveId3");
    }
}