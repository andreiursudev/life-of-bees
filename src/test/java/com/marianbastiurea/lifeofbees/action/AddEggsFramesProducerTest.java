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
import com.marianbastiurea.lifeofbees.bees.EggFrames;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

class AddEggsFramesProducerTest {

    private AddEggsFramesProducer producer;
    private LifeOfBees lifeOfBees;
    private Apiary apiary;

    @BeforeEach
    void setUp() {
        producer = new AddEggsFramesProducer();
        lifeOfBees = mock(LifeOfBees.class);
        apiary = mock(Apiary.class);

        when(lifeOfBees.getApiary()).thenReturn(apiary);
    }

    @Test
    void testProduce_WhenAllHivesCanAddEggFrames() {
        List<Hive> hives = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Hive hive = mock(Hive.class);
            when(hive.getId()).thenReturn(i);
            EggFrames eggFrames = mock(EggFrames.class);
            when(eggFrames.canAddNewEggsFrame()).thenReturn(true);
            when(hive.getEggFrames()).thenReturn(eggFrames);
            hives.add(hive);
        }
        when(apiary.getHives()).thenReturn(hives);
        Optional<List<Integer>> result = producer.produce(lifeOfBees);
        assertTrue(result.isPresent());
        assertEquals(3, result.get().size());
        assertTrue(result.get().containsAll(List.of(1, 2, 3)));
    }

    @Test
    void testProduce_WhenNoHiveCanAddEggFrames() {
        List<Hive> hives = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Hive hive = mock(Hive.class);
            EggFrames eggFrames = mock(EggFrames.class);
            when(eggFrames.canAddNewEggsFrame()).thenReturn(false);
            when(hive.getEggFrames()).thenReturn(eggFrames);
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
