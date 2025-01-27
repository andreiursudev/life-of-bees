package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;


import java.util.Optional;

public class FeedBeesProducer implements ActionOfTheWeekProducer<Boolean> {
    @Override
    public Optional<Boolean> produce(Hives hives) {
        boolean canFeed =hives.canFeedBees();
        return canFeed ? Optional.of(true) : Optional.empty();
    }


}
