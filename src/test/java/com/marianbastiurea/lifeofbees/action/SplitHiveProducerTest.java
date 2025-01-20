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
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SplitHiveProducerTest {

    @InjectMocks
    private SplitHiveProducer producer;

    @Mock
    private LifeOfBees lifeOfBees;

    @Mock
    private Apiary apiary;

    @Mock
    private Hive hive1;

    @Mock
    private Hive hive2;

    @Mock
    private Hive hive3;

    @Test
    void testProduce_WhenSomeHivesCanBeSplit() {
        // Arrange
        when(lifeOfBees.getApiary()).thenReturn(apiary);
        when(apiary.getHives()).thenReturn(List.of(hive1, hive2, hive3));

        when(hive1.checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate())).thenReturn(true);
        when(hive2.checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate())).thenReturn(false);
        when(hive3.checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate())).thenReturn(true);

        when(hive1.getId()).thenReturn(1);
        when(hive3.getId()).thenReturn(3);

        Optional<List<Integer>> result = producer.produce(lifeOfBees);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertTrue(result.get().containsAll(List.of(1, 3)));

        verify(hive1).checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate());
        verify(hive2).checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate());
        verify(hive3).checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate());
    }

    @Test
    void testProduce_WhenNoHivesCanBeSplit() {
        // Arrange
        when(lifeOfBees.getApiary()).thenReturn(apiary);
        when(apiary.getHives()).thenReturn(List.of(hive1, hive2, hive3));
        when(hive1.checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate())).thenReturn(false);
        when(hive2.checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate())).thenReturn(false);
        when(hive3.checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate())).thenReturn(false);
        Optional<List<Integer>> result = producer.produce(lifeOfBees);

        assertTrue(result.isEmpty());

        verify(hive1).checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate());
        verify(hive2).checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate());
        verify(hive3).checkIfHiveCouldBeSplit(lifeOfBees.getCurrentDate());
    }
}
