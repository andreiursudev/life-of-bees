package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.util.Optional;

public class FeedBeesProducer implements ActionOfTheWeekProducer<Integer> {

    @Override
    public Optional<Integer> produce(Hives hives) {
        Integer result = hives.checkFeedBees();
        return (result > 0) ? Optional.of(result) : Optional.empty();
    }
}
