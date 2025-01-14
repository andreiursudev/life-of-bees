import com.marianbastiurea.lifeofbees.bees.EggFrames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        Assertions.assertEquals(EggFrames.maxEggPerFrame, eggFrames.iterateOneDay(0));
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

}
