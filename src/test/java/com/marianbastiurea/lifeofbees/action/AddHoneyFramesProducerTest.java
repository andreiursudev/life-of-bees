package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.HoneyFrames;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddHoneyFramesProducerTest {
    /*

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
    void testProduce_WhenMixedHiveConditions() {
        List<Hive> hives = new ArrayList<>();
        Hive hive1 = mock(Hive.class);
        when(hive1.getId()).thenReturn(1);
        HoneyFrames honeyFrames1 = mock(HoneyFrames.class);
        when(honeyFrames1.canAddANewHoneyFrameInHive()).thenReturn(true);
        when(hive1.getHoneyFrames()).thenReturn(honeyFrames1);
        hives.add(hive1);
        Hive hive2 = mock(Hive.class);
        when(hive2.getId()).thenReturn(2);
        HoneyFrames honeyFrames2 = mock(HoneyFrames.class);
        when(honeyFrames2.canAddANewHoneyFrameInHive()).thenReturn(false);
        when(hive2.getHoneyFrames()).thenReturn(honeyFrames2);
        hives.add(hive2);

        when(apiary.getHives()).thenReturn(hives);


        Optional<List<Integer>> result = producer.produce(lifeOfBees);


        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertTrue(result.get().contains(1));
        assertFalse(result.get().contains(2));
    }


    @Test
    void testProduce_WhenSingleHiveCanAddHoneyFrame() {

        List<Hive> hives = new ArrayList<>();

        Hive hive = mock(Hive.class);
        when(hive.getId()).thenReturn(1);
        HoneyFrames honeyFrames = mock(HoneyFrames.class);
        when(honeyFrames.canAddANewHoneyFrameInHive()).thenReturn(true);
        when(hive.getHoneyFrames()).thenReturn(honeyFrames);
        hives.add(hive);

        when(apiary.getHives()).thenReturn(hives);


        Optional<List<Integer>> result = producer.produce(lifeOfBees);


        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertTrue(result.get().contains(1));
    }

    @Test
    void testProduce_WhenSingleHiveCannotAddHoneyFrame() {

        List<Hive> hives = new ArrayList<>();

        Hive hive = mock(Hive.class);
        when(hive.getId()).thenReturn(1);
        HoneyFrames honeyFrames = mock(HoneyFrames.class);
        when(honeyFrames.canAddANewHoneyFrameInHive()).thenReturn(false);
        when(hive.getHoneyFrames()).thenReturn(honeyFrames);
        hives.add(hive);

        when(apiary.getHives()).thenReturn(hives);


        Optional<List<Integer>> result = producer.produce(lifeOfBees);


        assertTrue(result.isEmpty());
    }

     */
}

