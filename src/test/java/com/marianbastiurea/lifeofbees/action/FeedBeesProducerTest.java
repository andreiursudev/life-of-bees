package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeedBeesProducerTest {

    private FeedBeesProducer producer;
    private LifeOfBees lifeOfBees;
    private Apiary apiary;
    private BeeTime currentDate;

    @BeforeEach
    void setUp() {
        producer = new FeedBeesProducer();
        lifeOfBees = mock(LifeOfBees.class);
        apiary = mock(Apiary.class);
        currentDate = mock(BeeTime.class);

        when(lifeOfBees.getApiary()).thenReturn(apiary);
        when(lifeOfBees.getCurrentDate()).thenReturn(currentDate);
    }

    @Test
    void testProduce_WhenCheckFeedBeesReturnsPositiveValue() {

        when(apiary.checkFeedBees(currentDate)).thenReturn(5);

        Optional<Integer> result = producer.produce(lifeOfBees);

        assertTrue(result.isPresent());
        assertEquals(5, result.get());
        verify(apiary, times(1)).checkFeedBees(currentDate);
    }

    @Test
    void testProduce_WhenCheckFeedBeesReturnsZero() {

        when(apiary.checkFeedBees(currentDate)).thenReturn(0);

        Optional<Integer> result = producer.produce(lifeOfBees);

        assertFalse(result.isPresent());
        verify(apiary, times(1)).checkFeedBees(currentDate);
    }

    @Test
    void testProduce_WhenCheckFeedBeesReturnsNegativeValue() {

        when(apiary.checkFeedBees(currentDate)).thenReturn(-1);

        Optional<Integer> result = producer.produce(lifeOfBees);

        assertFalse(result.isPresent());
        verify(apiary, times(1)).checkFeedBees(currentDate);
    }
}

