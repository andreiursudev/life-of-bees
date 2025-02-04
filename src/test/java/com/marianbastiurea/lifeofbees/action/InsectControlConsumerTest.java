package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.*;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsectControlConsumerTest {
    private static final Logger logger = LoggerFactory.getLogger(InsectControlConsumerTest.class);


    @Test
    void answerIsYesThatMeansBeeKeeperHaveToPay() {
        InsectControlConsumer insectControlConsumer = new InsectControlConsumer();

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
        insectControlConsumer.accept(lifeOfBees, "yes");
        double result = lifeOfBees.getMoneyInTheBank();
        assertEquals(70, result);

    }

    @Test
    void answerIsNoThatMeansLastTwoBeesBatchesAreDead() {
        InsectControlConsumer insectControlConsumer = new InsectControlConsumer();
        BeesBatches initialBeesBatches = createBeesBatches(30, 100);
        BeesBatches finalBeesBatches = createBeesBatches(30, 70);

        Hives initialHive = new Hives(
                new Hive(1, true, new EggFrames(), new HoneyFrames(),
                        initialBeesBatches, new ArrayList<>(), new Queen(1), true));
        Hives finalHive = new Hives(
                new Hive(1, true, new EggFrames(), new HoneyFrames(),
                        finalBeesBatches, new ArrayList<>(), new Queen(1,0.7), true));
        Apiary apiary = new Apiary(initialHive);
        LifeOfBees lifeOfBees = new LifeOfBees(
                "Test Game", "user123", "private", apiary,
                "Test Location", new WeatherData(), 100.0, 0.0, new ActionsOfTheWeek()
        );

        insectControlConsumer.accept(lifeOfBees, "no");
        assertEquals(finalHive, apiary.getHives());
    }


    @Test
    void answerIsNoThatMeansFeedBeesIndexIsChanging() {
        InsectControlConsumer insectControlConsumer = new InsectControlConsumer();

        BeesBatches finalBeesBatches = createBeesBatches(30, 70);
        Hives finalHive = new Hives(
                new Hive(1, true, new EggFrames(), new HoneyFrames(),
                        finalBeesBatches, new ArrayList<>(), new Queen(1), true));
        logger.info("this is the Queen before: {}", finalHive.getHives().getFirst().getQueen());

        Apiary apiary = new Apiary(finalHive);
        LifeOfBees lifeOfBees = new LifeOfBees(
                "Test Game", "user123", "private", apiary,
                "Test Location", new WeatherData(), 100.0, 0.0, new ActionsOfTheWeek()
        );

        insectControlConsumer.accept(lifeOfBees, "no");
        logger.info("This is the Queen after: {}", finalHive.getHives().getFirst().getQueen());
        assertEquals(finalHive, apiary.getHives());


    }

    private static BeesBatches createBeesBatches(int days, int eggs) {
        BeesBatches beesBatches = new BeesBatches();
        for (int i = 0; i < days; i++) {
            beesBatches.add(eggs);
        }
        return beesBatches;
    }

}