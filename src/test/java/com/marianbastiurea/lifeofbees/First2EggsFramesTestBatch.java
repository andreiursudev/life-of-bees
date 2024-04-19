package com.marianbastiurea.lifeofbees;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;

public class First2EggsFramesTestBatch {

    @Test
    void given2FramesForLayingEggsShouldReturn13March() {
        LocalDate expectedDate = LocalDate.of(LocalDate.now().getYear(), 3, 13);
        assertEquals(expectedDate,Hive.layEggs(Hive.getDailyEggProduction(Month.MARCH, 1),2));
    }
}