package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.util.Optional;

public class FeedBeesProducer implements ActionOfTheWeekProducer<Integer> {

    @Override
    public Optional<Integer> produce(LifeOfBees lifeOfBees) {
        Apiary apiary = lifeOfBees.getApiary();
        BeeTime currentDate = lifeOfBees.getCurrentDate();
        Integer result = apiary.getHives().checkFeedBees(currentDate);
        return (result > 0) ? Optional.of(result) : Optional.empty();
    }
}
