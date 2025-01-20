package com.marianbastiurea.lifeofbees.action;
import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsectControllerConsumerTest {

    @InjectMocks
    private InsectControllerConsumer consumer;

    @Mock
    private LifeOfBees lifeOfBees;

    @Mock
    private Apiary apiary;

    @Test
    void testAccept_WhenAnswerIsYes() {

        when(lifeOfBees.getApiary()).thenReturn(apiary);
        when(apiary.getHives()).thenReturn(List.of(
                mock(Hive.class), mock(Hive.class), mock(Hive.class), mock(Hive.class), mock(Hive.class) // 5 stupi
        ));
        when(lifeOfBees.getMoneyInTheBank()).thenReturn(100.00); // Suma inițială: 100

        consumer.accept(lifeOfBees, "yes");

        verify(lifeOfBees, times(1)).setMoneyInTheBank(100 - (5 * 10.00)); // Cost: 50
        verify(apiary, never()).removeLastTwoBeesBatches();
    }


    @Test
    void testAccept_WhenAnswerIsNo() {

        when(lifeOfBees.getApiary()).thenReturn(apiary);

        consumer.accept(lifeOfBees, "no");

        verify(apiary, times(1)).removeLastTwoBeesBatches();
        verify(lifeOfBees, never()).setMoneyInTheBank(anyInt());
    }

    @Test
    void testAccept_WhenAnswerIsInvalid() {

        when(lifeOfBees.getApiary()).thenReturn(apiary);

        consumer.accept(lifeOfBees, "invalid");

        verify(apiary, times(1)).removeLastTwoBeesBatches();
        verify(lifeOfBees, never()).setMoneyInTheBank(anyInt());
    }
}
