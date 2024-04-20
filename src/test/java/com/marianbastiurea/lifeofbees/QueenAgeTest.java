package com.marianbastiurea.lifeofbees;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class QueenAgeTest {
    @Test
    void given4AsQueenAgeAnd1AsOptionShouldReturn1(){
        int ageOfQueen=4;
        String answer=Queen.ageOfQueenIndex(ageOfQueen);
        assertEquals(1,Queen.ageOfQueenIndex(ageOfQueen));

    }
}
