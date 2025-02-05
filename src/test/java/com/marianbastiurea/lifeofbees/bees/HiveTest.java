package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HiveTest {
    @Test
    void wrongTimeToChangeQueen() {
        BeeTime currentDate = new BeeTime(2023, 5, 2);
        RandomParameters randomNumber = new RandomParameters(0.2);
        Queen initialQueen = new Queen(3);
        Hive hive = new Hive(initialQueen);
        hive.maybeChangeQueen(currentDate, randomNumber);
        assertEquals(initialQueen, hive.getQueen(), "Queen should not be changed");
    }

    @Test
    void wrongAgeToChangeQueen() {
        BeeTime currentDate = new BeeTime(2023, 5, 1);
        RandomParameters randomNumber = new RandomParameters(0.5);
        Queen initialQueen = new Queen(3);
        Hive hive = new Hive(initialQueen);
        hive.maybeChangeQueen(currentDate, randomNumber);
        assertEquals(initialQueen, hive.getQueen(), "Queen should not be changed");
    }

    @Test
    void changeQueen() {
        BeeTime currentDate = new BeeTime(2023, 5, 1);
        RandomParameters randomNumber = new RandomParameters(0.2);
        Queen initialQueen = new Queen(3);
        Hive hive = new Hive(initialQueen);
        hive.maybeChangeQueen(currentDate, randomNumber);
        Queen actualQueen = new Queen(0);
        assertEquals(actualQueen, hive.getQueen(), "Queen should be changed");
    }


}

