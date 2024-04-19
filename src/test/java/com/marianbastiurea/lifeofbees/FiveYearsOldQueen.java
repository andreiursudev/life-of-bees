package com.marianbastiurea.lifeofbees;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FiveYearsOldQueen {
    @Test
    void given5FiveYearsOldQueenShouldReturnQuarterAsIndex(){
        assertEquals(0.25,Queen.ageOfQueenIndex(5) );
    }

}
