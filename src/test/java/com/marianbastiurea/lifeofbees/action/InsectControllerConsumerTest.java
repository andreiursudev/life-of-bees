package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.*;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InsectControllerConsumerTest {
    private static final Logger logger = LoggerFactory.getLogger(InsectControllerConsumerTest.class);


    @Test
    void answerIsYesThatMeansBeeKeeperHaveToPay() {
        InsectControllerConsumer insectControllerConsumer = new InsectControllerConsumer();

        Hives hives = new Hives(
                new Hive(1, new HoneyFrames(4, 5)),
                new Hive(2, new HoneyFrames(3, 3)),
                new Hive(3, new HoneyFrames(2, 5))
        );
        Apiary apiary = new Apiary(hives);
        LifeOfBees lifeOfBees = new LifeOfBees(
                "Test Game", "user123", "private", apiary,
                "Test Location", new WeatherData(), 100.0, 0.0, new ActionsOfTheWeek()
        );
        insectControllerConsumer.accept(lifeOfBees,"yes");
        double result=lifeOfBees.getMoneyInTheBank();
        assertEquals(70,result);

    }

    @Test
    void answerIsNoThatMeansLastTwoBeesBatchesAreDeadAndFeedBeesIndexIsChanging() {
        InsectControllerConsumer insectControllerConsumer = new InsectControllerConsumer();
        BeesBatches initialBeesBatches=createBeesBatches(30,100);
        BeesBatches finalBeesBatches=createBeesBatches(30,70);

        Hives initialHive = new Hives(
                new Hive(1, true, new EggFrames(), new HoneyFrames(),
                        initialBeesBatches, new ArrayList<>(), new Queen(1), true));
        Hives finalHive = new Hives(
                new Hive(1, true, new EggFrames(), new HoneyFrames(),
                        finalBeesBatches, new ArrayList<>(), new Queen(1), true));

        Apiary apiary = new Apiary(initialHive);
        LifeOfBees lifeOfBees = new LifeOfBees(
                "Test Game", "user123", "private", apiary,
                "Test Location", new WeatherData(), 100.0, 0.0, new ActionsOfTheWeek()
        );
        int initialNumberOfEggs = lifeOfBees.getApiary().getHives().getHives().getFirst().getQueen().makeEggs(1, 1);
        logger.info("eggs number before : " + initialNumberOfEggs);

        insectControllerConsumer.accept(lifeOfBees,"no");
        assertEquals(finalHive,apiary.getHives());

        int actualNumberOfEggs = lifeOfBees.getApiary().getHives().getHives().getFirst().getQueen().makeEggs(1, 1);
        logger.info("eggs number after: " + actualNumberOfEggs);
        assertTrue(actualNumberOfEggs < initialNumberOfEggs);


    }

    private static BeesBatches createBeesBatches(int days, int eggs) {
        BeesBatches beesBatches = new BeesBatches();
        for (int i = 0; i < days; i++) {
            beesBatches.add(eggs);
        }
        return beesBatches;
    }

}