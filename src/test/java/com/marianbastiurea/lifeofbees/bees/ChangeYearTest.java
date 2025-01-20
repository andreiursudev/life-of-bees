package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeYearTest {
    @Test
    void changeYearFrom2024To2025(){
        BeeTime beeTime = new BeeTime(2024, 3, 1);
        beeTime.changeYear();
        LocalDate expectedDate = LocalDate.of(2025, 3, 1);
        assertEquals(expectedDate, beeTime.getLocalDate(), "Data nu este corectă după schimbarea anului!");

    }
}
