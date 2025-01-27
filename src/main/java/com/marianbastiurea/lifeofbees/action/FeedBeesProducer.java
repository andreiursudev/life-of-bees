package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hives;


import java.util.Optional;

public class FeedBeesProducer implements ActionOfTheWeekProducer<Boolean> {

    @Override
    public Optional<Boolean> produce(Hives hives) {
        return hives.canFeedBees() ? Optional.of(true) : Optional.of(false);
    }

}
