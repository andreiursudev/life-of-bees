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

        Optional<List<List<Integer>>> result = producer.produce(hiveCollection);

        Optional<List<MoveEggFramePairHives>> expected = Optional.of(
                List.of(new MoveEggFramePairHives(1, 2))
        );

        assertEquals(expected, result.map(list ->
                list.stream()
                        .map(pair -> new MoveEggFramePairHives(pair.get(0), pair.get(1)))
                        .toList()
        ));
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
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection)
                .map(list -> list.stream()
                        .map(pair -> new MoveEggFramePairHives(pair.get(0), pair.get(1)))
                        .toList());
        assertEquals(Optional.empty(), result);
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
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection)
                .map(list -> list.stream()
                        .map(pair -> new MoveEggFramePairHives(pair.get(0), pair.get(1)))
                        .toList());

        assertEquals(Optional.empty(), result);
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
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection)
                .map(list -> list.stream()
                        .map(pair -> new MoveEggFramePairHives(pair.getFirst(), pair.get(1)))
                        .toList());
        assertEquals(Optional.empty(), result);
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
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection)
                .map(list -> list.stream()
                        .map(pair -> new MoveEggFramePairHives(pair.getFirst(), pair.get(1)))
                        .toList());
        assertEquals(Optional.empty(), result);
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
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection)
                .map(list -> list.stream()
                        .map(pair -> new MoveEggFramePairHives(pair.getFirst(), pair.get(1)))
                        .toList());
        assertEquals(Optional.empty(), result);
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
        Optional<List<MoveEggFramePairHives>> result = producer.produce(hiveCollection)
                .map(list -> list.stream()
                        .map(pair -> new MoveEggFramePairHives(pair.getFirst(), pair.get(1)))
                        .toList());
        assertEquals(Optional.of(Arrays.asList(
                new MoveEggFramePairHives(1, 2),
                new MoveEggFramePairHives(1, 3)
        )), result);
    }


}