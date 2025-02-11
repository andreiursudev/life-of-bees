package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.*;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.weather.WeatherData;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FeedBeesConsumerTest {
    private static final Logger logger = LoggerFactory.getLogger(FeedBeesConsumerTest.class);

    @Test
    void answerIsYesThatMeansBeeKeeperHaveToPay() {
        FeedBeesConsumer feedBeesConsumer = new FeedBeesConsumer();

        LifeOfBees lifeOfBees = new LifeOfBees(
                "Test Game", "user123", "private", new Apiary(new Hives(
                new Hive(1, new HoneyFrames(4, 5)),
                new Hive(2, new HoneyFrames(3, 3)),
                new Hive(3, new HoneyFrames(2, 5))
        )),
                "Test Location", new WeatherData(), 100.0, 0.0, new ActionsOfTheWeek()
        );
        feedBeesConsumer.accept(lifeOfBees, "yes");
        double result = lifeOfBees.getMoneyInTheBank();
        assertEquals(70, result);

    }

    @Test
    void answerIsNoThatMeansFeedBeesIndexChanged() {
        FeedBeesConsumer feedBeesConsumer = new FeedBeesConsumer();
        Hives finalHive = new Hives(
                new Hive(1, true, new EggFrames(), new HoneyFrames(),
                        new BeesBatches(), new ArrayList<>(), new Queen(1), true));
        logger.info("this is the Queen before: {}", finalHive.getHives().getFirst().getQueen());
        Apiary apiary = new Apiary(finalHive);
        LifeOfBees lifeOfBees = new LifeOfBees(
                "Test Game", "user123", "private", apiary,
                "Test Location", new WeatherData(), 100.0, 0.0, new ActionsOfTheWeek()
        );
        feedBeesConsumer.accept(lifeOfBees, "no");
        logger.info("this is the Queen after: {}", finalHive.getHives().getFirst().getQueen());
        assertEquals(finalHive, apiary.getHives());

    }
}