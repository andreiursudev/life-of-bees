package com.marianbastiurea.lifeofbees.bees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static com.marianbastiurea.lifeofbees.bees.EggFrames.maxEggPerFrame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class EggFramesTest {

    @Test
    void eggsHatchAfter20Days() {
        EggFrames eggFrames = new EggFrames(3);

        for (int i = 0; i < 20; i++) {
            eggFrames.iterateOneDay(i);
        }

        for (int i = 0; i < 20; i++) {
            Assertions.assertEquals(i, eggFrames.iterateOneDay(0));
        }
    }

    @Test
    void maxEggsCannotBeExceed() {
        EggFrames eggFrames = new EggFrames(1);
        eggFrames.iterateOneDay(6500);

        for (int i = 0; i < 19; i++) {
            Assertions.assertEquals(0, eggFrames.iterateOneDay(0));
        }
        Assertions.assertEquals(maxEggPerFrame, eggFrames.iterateOneDay(0));
    }

    @Test
    void isSplitEggFramesOk() {
        EggFrames eggFrames = new EggFrames(1);
        for (int i = 0; i < 20; i++) {
            eggFrames.iterateOneDay(100);
        }
        EggFrames newEggFrames=eggFrames.splitEggFrames();
        for (int i = 0; i < 20; i++) {
            assertEquals(50, eggFrames.iterateOneDay(0));
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(50, newEggFrames.iterateOneDay(0));

        }

    }

    @Test
    public void testExtractEggBatchesForFrame() {

        Random random = new Random();
         EggFrames eggFrames = new EggFrames(random.nextInt(3, 5));
        random.ints(20, 800, 901).forEach(eggFrames::iterateOneDay);
        int initialNumberOfEggFrames = eggFrames.getNumberOfEggFrames();
        int initialTotalEggs=eggFrames.getEggs();
        eggFrames.extractEggBatchesForFrame();
        assertEquals(initialNumberOfEggFrames - 1, eggFrames.getNumberOfEggFrames(),
                "The number of egg frames should be reduced by 1 after extraction.");

        int currentSum = 0;
        for (int i = 0; i < 1; i++) {
            currentSum += eggFrames.eggsByDay.get(i);
            if (currentSum <= maxEggPerFrame) {
                assertEquals(0, eggFrames.eggsByDay.get(i),
                        "Eggs in the extracted range should be set to 0.");
            } else {
                break;
            }
        }
        int remainingTotalEggs = eggFrames.getEggs();
        int extractedEggs = initialTotalEggs - remainingTotalEggs;
        assertTrue(extractedEggs <= maxEggPerFrame,
                "The total number of extracted eggs should not exceed the maximum capacity per frame.");
    }



}
