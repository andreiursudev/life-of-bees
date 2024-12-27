import com.marianbastiurea.lifeofbees.bees.EggFrames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EggFramesTest {

    @Test
    void eggsHatchAfter20Days() {
        EggFrames eggFrames = new EggFrames(3);

        for (int i = 0; i < 20; i++) {
            eggFrames.ageOneDay(i);
        }

        for (int i = 0; i < 20; i++) {
            Assertions.assertEquals(i, eggFrames.ageOneDay(0));
        }
    }

    @Test
    void maxEggsCannotBeExceed() {
        EggFrames eggFrames = new EggFrames(1);
        eggFrames.ageOneDay(6500);

        for (int i = 0; i < 19; i++) {
            Assertions.assertEquals(0, eggFrames.ageOneDay(0));
        }
        Assertions.assertEquals(EggFrames.maxEggPerFrame, eggFrames.ageOneDay(0));
    }

    @Test
    void isSplitEggFramesOk() {
        EggFrames eggFrames = new EggFrames(1);
        for (int i = 0; i < 20; i++) {
            eggFrames.ageOneDay(100);
        }
        EggFrames newEggFrames=eggFrames.splitEggFrames();
        for (int i = 0; i < 20; i++) {
            assertEquals(50, eggFrames.ageOneDay(0));
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(50, newEggFrames.ageOneDay(0));

        }

    }

}
