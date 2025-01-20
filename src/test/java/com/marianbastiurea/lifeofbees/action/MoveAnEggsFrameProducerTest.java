package com.marianbastiurea.lifeofbees.action;
import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class MoveAnEggsFrameProducerTest {

    @InjectMocks
    private MoveAnEggsFrameProducer producer;

    @Mock
    private LifeOfBees lifeOfBees;

    @Mock
    private Apiary apiary;

    @Test
    void testProduce_WhenFramesCanBeMoved() {
        List<List<Integer>> mockResult = List.of(List.of(1, 2, 3), List.of(4, 5, 6));
        when(lifeOfBees.getApiary()).thenReturn(apiary);
        when(apiary.checkIfCanMoveAnEggsFrame()).thenReturn(mockResult);
        Optional<List<List<Integer>>> result = producer.produce(lifeOfBees);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals(List.of(1, 2, 3), result.get().get(0));
        assertEquals(List.of(4, 5, 6), result.get().get(1));
    }

    @Test
    void testProduce_WhenNoFramesCanBeMoved() {
        when(lifeOfBees.getApiary()).thenReturn(apiary);
        when(apiary.checkIfCanMoveAnEggsFrame()).thenReturn(List.of());
        Optional<List<List<Integer>>> result = producer.produce(lifeOfBees);
        assertTrue(result.isEmpty());
    }
}
