package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;


class FeedBeesConsumerTest {

    private FeedBeesConsumer consumer;
    private LifeOfBees lifeOfBees;
    private Apiary apiary;

    @BeforeEach
    void setUp() {
        consumer = new FeedBeesConsumer();
        lifeOfBees = mock(LifeOfBees.class);
        apiary = mock(Apiary.class);

        when(lifeOfBees.getApiary()).thenReturn(apiary);
    }

    @Test
    void testAccept_WhenAnswerIsYes() {

        List<Hive> mockedHives = mock(List.class);
        when(mockedHives.size()).thenReturn(5);
        when(apiary.getHives()).thenReturn(mockedHives);
        when(lifeOfBees.getMoneyInTheBank()).thenReturn(100.00);

        consumer.accept(lifeOfBees, "yes");

        verify(apiary, never()).removeLastTwoBeesBatches();
        verify(lifeOfBees, times(1)).setMoneyInTheBank(50.00);
    }


    @Test
    void testAccept_WhenAnswerIsNotYes() {
        consumer.accept(lifeOfBees, "no");
        verify(apiary, times(1)).removeLastTwoBeesBatches(); // Metoda de eliminare se apelează
        verify(lifeOfBees, never()).setMoneyInTheBank(anyInt()); // Nu se modifică banii din bancă
    }

    @Test
    void testAccept_WhenAnswerIsNull() {

        consumer.accept(lifeOfBees, null);

        verify(apiary, times(1)).removeLastTwoBeesBatches(); // Metoda de eliminare se apelează
        verify(lifeOfBees, never()).setMoneyInTheBank(anyInt()); // Nu se modifică banii din bancă
    }
}
