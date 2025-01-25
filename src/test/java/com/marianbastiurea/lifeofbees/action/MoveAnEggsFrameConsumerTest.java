package com.marianbastiurea.lifeofbees.action;
import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoveAnEggsFrameConsumerTest {

    @InjectMocks
    private MoveAnEggsFrameConsumer consumer;

    @Mock
    private LifeOfBees lifeOfBees;

    @Mock
    private Apiary apiary;

//    @Test
//    void testAccept() {
//        List<List<Integer>> hiveIdPairs = List.of(
//                List.of(1, 2),
//                List.of(3, 4)
//        );
//        when(lifeOfBees.getApiary()).thenReturn(apiary);
//        consumer.accept(lifeOfBees, hiveIdPairs);
//        verify(apiary, times(1)).moveAnEggsFrame(hiveIdPairs);
//    }
}
