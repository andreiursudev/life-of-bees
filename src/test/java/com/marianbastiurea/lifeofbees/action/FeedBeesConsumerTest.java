package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.*;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class FeedBeesConsumerTest {

    @Test
    void answerIsYesThatMeansBeeKeeperHaveToPay() {
        FeedBeesConsumer feedBeesConsumer = new FeedBeesConsumer();

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
        feedBeesConsumer.accept(lifeOfBees,"yes");
        double result=lifeOfBees.getMoneyInTheBank();
        assertEquals(70,result);

    }

    @Test
    void answerIsNoThatMeansLastTwoBeesBatchesAreDead() {
        FeedBeesConsumer feedBeesConsumer = new FeedBeesConsumer();
        BeesBatches initialBeesBatches=createBeesBatches(20,10);
        BeesBatches finalBeesBatches=createBeesBatches(18,10);

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
        feedBeesConsumer.accept(lifeOfBees,"no");
        assertEquals(finalHive,apiary.getHives());


    }

    private static BeesBatches createBeesBatches(int x, int e) {
        BeesBatches beesBatches = new BeesBatches();
        for (int i = 0; i < x; i++) {
            beesBatches.add(e);
        }
        return beesBatches;
    }
}