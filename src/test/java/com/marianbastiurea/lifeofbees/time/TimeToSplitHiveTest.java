package com.marianbastiurea.lifeofbees.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeToSplitHiveTest {

    @Test
    void testTimeToSplitHiveWithValidDate() {
        BeeTime beeTime = new BeeTime();
        beeTime.setCurrentDate(LocalDate.of(2025, Month.MAY, 10)); // 10 mai
        assertTrue(beeTime.timeToSplitHive(), "10 mai ar trebui să permită divizarea stupului.");
}

    @Test
    void testTimeToSplitHiveWithInvalidDate(){
        BeeTime beeTime = new BeeTime();
        beeTime.setCurrentDate(LocalDate.of(2025, Month.SEPTEMBER, 15)); // 15 septembrie
        assertFalse(beeTime.timeToSplitHive(), "15 septembrie nu ar trebui să permită divizarea stupului.");

    }

}
