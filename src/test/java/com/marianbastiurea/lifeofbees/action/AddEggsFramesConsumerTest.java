package com.marianbastiurea.lifeofbees.action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.HoneyFrames;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;


class AddHoneyFramesProducerTest {

    private AddHoneyFramesProducer producer;
    private LifeOfBees lifeOfBees;
    private Apiary apiary;

    @BeforeEach
    void setUp() {
        producer = new AddHoneyFramesProducer();
        lifeOfBees = mock(LifeOfBees.class);
        apiary = mock(Apiary.class);

        when(lifeOfBees.getApiary()).thenReturn(apiary);
    }

    @Test
    void testProduce_WhenAllHivesCanAddHoneyFrames() {
        List<Hive> hives = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Hive hive = mock(Hive.class);
            when(hive.getId()).thenReturn(i);
            HoneyFrames honeyFrames = mock(HoneyFrames.class);
            when(honeyFrames.canAddANewHoneyFrameInHive()).thenReturn(true);
            when(hive.getHoneyFrames()).thenReturn(honeyFrames);
            hives.add(hive);
        }
        when(apiary.getHives()).thenReturn(hives);
        Optional<List<Integer>> result = producer.produce(lifeOfBees);
        assertTrue(result.isPresent());
        assertEquals(3, result.get().size());
        assertTrue(result.get().containsAll(List.of(1, 2, 3)));
    }

    @Test
    void testProduce_WhenNoHiveCanAddHoneyFrames() {
        // Arrange
        List<Hive> hives = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Hive hive = mock(Hive.class);
            HoneyFrames honeyFrames = mock(HoneyFrames.class);
            when(honeyFrames.canAddANewHoneyFrameInHive()).thenReturn(false);
            when(hive.getHoneyFrames()).thenReturn(honeyFrames);
            hives.add(hive);
        }
        when(apiary.getHives()).thenReturn(hives);
        Optional<List<Integer>> result = producer.produce(lifeOfBees);
        assertTrue(result.isEmpty());
    }

    @Test
    void testProduce_WhenApiaryHasNoHives() {
        when(apiary.getHives()).thenReturn(new ArrayList<>());
        Optional<List<Integer>> result = producer.produce(lifeOfBees);
        assertTrue(result.isEmpty());
    }
}
