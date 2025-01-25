package com.marianbastiurea.lifeofbees.action;
import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InsectControlProducerTest {
    /*

    @InjectMocks
    private InsectControlProducer producer;

    @Mock
    private LifeOfBees lifeOfBees;

    @Mock
    private Apiary apiary;

    @Mock
    private BeeTime currentDate;

    @Test
    void testProduce_WhenInsectControlNeeded() {
        when(lifeOfBees.getApiary()).thenReturn(apiary);
        when(lifeOfBees.getCurrentDate()).thenReturn(currentDate);
        when(apiary.checkInsectControl(currentDate)).thenReturn(5);
        Optional<Integer> result = producer.produce(lifeOfBees);
        assertTrue(result.isPresent());
        assertEquals(5, result.get());
    }

    @Test
    void testProduce_WhenNoInsectControlNeeded() {
        when(lifeOfBees.getApiary()).thenReturn(apiary);
        when(lifeOfBees.getCurrentDate()).thenReturn(currentDate);
        when(apiary.checkInsectControl(currentDate)).thenReturn(0);
        Optional<Integer> result = producer.produce(lifeOfBees);
        assertTrue(result.isEmpty());
    }

     */
}
