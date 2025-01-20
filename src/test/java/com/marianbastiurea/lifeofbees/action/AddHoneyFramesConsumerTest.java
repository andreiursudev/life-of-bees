package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.HoneyFrames;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class AddHoneyFramesConsumerTest {

    private AddHoneyFramesConsumer consumer;
    private LifeOfBees lifeOfBees;
    private Apiary apiary;

    @BeforeEach
    void setUp() {
        consumer = new AddHoneyFramesConsumer();
        lifeOfBees = mock(LifeOfBees.class);
        apiary = mock(Apiary.class);

        when(lifeOfBees.getApiary()).thenReturn(apiary);
    }

    @Test
    void testAccept_WhenHoneyHiveIdsIsNull() {
        consumer.accept(lifeOfBees, null);
        verify(apiary, never()).getHiveById(anyInt());
    }

    @Test
    void testAccept_WhenHoneyHiveIdsIsEmpty() {
        consumer.accept(lifeOfBees, Collections.emptyList());
        verify(apiary, never()).getHiveById(anyInt());
    }

    @Test
    void testAccept_WhenHoneyHiveIdsContainsValidIds() {
        Hive hive1 = mock(Hive.class);
        Hive hive2 = mock(Hive.class);
        HoneyFrames honeyFrames1 = mock(HoneyFrames.class);
        HoneyFrames honeyFrames2 = mock(HoneyFrames.class);
        when(hive1.getHoneyFrames()).thenReturn(honeyFrames1);
        when(hive2.getHoneyFrames()).thenReturn(honeyFrames2);
        when(apiary.getHiveById(1)).thenReturn(hive1);
        when(apiary.getHiveById(2)).thenReturn(hive2);
        List<Integer> honeyHiveIds = Arrays.asList(1, 2);
        consumer.accept(lifeOfBees, honeyHiveIds);
        verify(honeyFrames1, times(1)).addNewHoneyFrameInHive();
        verify(honeyFrames2, times(1)).addNewHoneyFrameInHive();
    }

    @Test
    void testAccept_WhenHoneyHiveIdsContainsInvalidId() {
        when(apiary.getHiveById(1)).thenReturn(null);
        List<Integer> honeyHiveIds = Collections.singletonList(1);
        consumer.accept(lifeOfBees, honeyHiveIds);
        verify(apiary, times(1)).getHiveById(1);
    }

    @Test
    void testAccept_WhenHoneyHiveIdsContainsMixedIds() {
        Hive hive2 = mock(Hive.class);
        HoneyFrames honeyFrames2 = mock(HoneyFrames.class);
        when(hive2.getHoneyFrames()).thenReturn(honeyFrames2);
        when(apiary.getHiveById(1)).thenReturn(null);
        when(apiary.getHiveById(2)).thenReturn(hive2);
        List<Integer> honeyHiveIds = Arrays.asList(1, 2);
        consumer.accept(lifeOfBees, honeyHiveIds);
        verify(apiary, times(1)).getHiveById(1);
        verify(apiary, times(1)).getHiveById(2);
        verify(honeyFrames2, times(1)).addNewHoneyFrameInHive();
    }
}
