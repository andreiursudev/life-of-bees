package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HiveTest {
    @Test
    void wrongTimeToChangeQueen() {
        BeeTime currentDate = new BeeTime(2023, 5, 2);
        RandomParameters randomParameters = mock(RandomParameters.class);
        when(randomParameters.chanceToChangeQueen()).thenReturn(0.2);

        Queen initialQueen = new Queen(3);
        Hive hive = new Hive(initialQueen);
        hive.maybeChangeQueen(currentDate, randomParameters);

        assertEquals(initialQueen, hive.getQueen(), "Queen should not be changed");
    }

    @Test
    void wrongAgeToChangeQueen() {
        BeeTime currentDate = new BeeTime(2023, 5, 1);
        RandomParameters randomParameters = mock(RandomParameters.class);
        when(randomParameters.chanceToChangeQueen()).thenReturn(0.5);

        Queen initialQueen = new Queen(3);
        Hive hive = new Hive(initialQueen);
        hive.maybeChangeQueen(currentDate, randomParameters);

        assertEquals(initialQueen, hive.getQueen(), "Queen should not be changed");
    }


    @Test
    void changeQueen() {
        BeeTime currentDate = new BeeTime(2023, 5, 1);
        RandomParameters randomParameters = mock(RandomParameters.class);
        when(randomParameters.chanceToChangeQueen()).thenReturn(0.2);

        Queen initialQueen = new Queen(3);
        Hive hive = new Hive(initialQueen);
        hive.maybeChangeQueen(currentDate, randomParameters);

        assertNotEquals(initialQueen, hive.getQueen(), "Queen should be changed");
        assertEquals(0, hive.getQueen().getAgeOfQueen(), "New queen should have age 0");
    }

    @Test
    void returnProductivity1ForHoneyTypeAcacia(){
        BeeTime currentDate=new BeeTime(2023,6,1);
        double productivity=currentDate.honeyType().getProductivity();
        assertEquals(1, productivity);

    }

    @Test
    void ifProductivityIs1AndWeatherIndexIs1QueenShouldMake1Eggs(){

    }

}

