package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class AddEggsFramesConsumerTest {
/*
    private AddEggsFramesConsumer consumer;
    private LifeOfBees lifeOfBees;
    private Apiary apiary;

    @BeforeEach
    void setUp() {
        consumer = new AddEggsFramesConsumer();
        lifeOfBees = mock(LifeOfBees.class);
        apiary = mock(Apiary.class);

        when(lifeOfBees.getApiary()).thenReturn(apiary);
    }

    @Test
    void testAccept_WhenEggHiveIdsIsNull() {
        consumer.accept(lifeOfBees, null);
        verify(apiary, never()).getHiveById(anyInt());
    }

    @Test
    void testAccept_WhenEggHiveIdsIsEmpty() {
        consumer.accept(lifeOfBees, Collections.emptyList());
        verify(apiary, never()).getHiveById(anyInt());
    }

    @Test
    void testAccept_WhenEggHiveIdsContainsValidIds() {
        Hive hive1 = mock(Hive.class);
        Hive hive2 = mock(Hive.class);

        when(apiary.getHiveById(1)).thenReturn(hive1);
        when(apiary.getHiveById(2)).thenReturn(hive2);

        List<Integer> eggHiveIds = Arrays.asList(1, 2);

        consumer.accept(lifeOfBees, eggHiveIds);
        verify(hive1, times(1)).addNewEggsFrameInHive();
        verify(hive2, times(1)).addNewEggsFrameInHive();
    }

    @Test
    void testAccept_WhenEggHiveIdsContainsInvalidId() {
        when(apiary.getHiveById(1)).thenReturn(null);
        List<Integer> eggHiveIds = Collections.singletonList(1);
        consumer.accept(lifeOfBees, eggHiveIds);
        verify(apiary, times(1)).getHiveById(1);
    }

    @Test
    void testAccept_WhenEggHiveIdsContainsMixedIds() {
        Hive hive2 = mock(Hive.class);
        when(apiary.getHiveById(1)).thenReturn(null);
        when(apiary.getHiveById(2)).thenReturn(hive2);
        List<Integer> eggHiveIds = Arrays.asList(1, 2);
        consumer.accept(lifeOfBees, eggHiveIds);
        verify(apiary, times(1)).getHiveById(1);
        verify(apiary, times(1)).getHiveById(2);
        verify(hive2, times(1)).addNewEggsFrameInHive();
    }
    */

}
