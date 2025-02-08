package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QueenTest {

    private RandomParameters randomParameters;
    private Queen initialQueen;

    @BeforeEach
    void setUp() {
        randomParameters = mock(RandomParameters.class);
        initialQueen = new Queen(3);
    }

    private void testQueenChange(double chance, BeeTime beeTime, boolean shouldChange) {
        when(randomParameters.chanceToChangeQueen()).thenReturn(chance);
        Hive hive = new Hive(initialQueen);
        hive.maybeChangeQueen(beeTime, randomParameters);
        assertEquals(shouldChange ? 0 : initialQueen.getAgeOfQueen(),
                hive.getQueen().getAgeOfQueen(),
                shouldChange ? "New queen should have age 0" : "Queen should not be changed");
    }

    @Test
    void wrongTimeToChangeQueen() {
        testQueenChange(0.2, new BeeTime(2023, 5, 2), false);
    }

    @Test
    void wrongAgeToChangeQueen() {
        testQueenChange(0.5, new BeeTime(2023, 5, 1), false);
    }

    @Test
    void changeQueen() {
        testQueenChange(0.2, new BeeTime(2023, 5, 1), true);
    }

    @Test
    void ifProductivityIs1AndWeatherIndexIs1QueenShouldLaid2000Eggs() {
        Queen queen = new Queen(1, 1);
        int numberOfEggs = queen.makeEggs(1,1);
        assertEquals(numberOfEggs, 2000);
    }
}
