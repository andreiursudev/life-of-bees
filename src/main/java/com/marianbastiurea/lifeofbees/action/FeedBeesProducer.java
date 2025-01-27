package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hives;


import java.util.Optional;

public class FeedBeesProducer implements ActionOfTheWeekProducer<Integer> {

    @Override
    public Optional<Integer> produce(Hives hives) {
        Integer result = hives.checkFeedBees();
        return (result > 0) ? Optional.of(result) : Optional.empty();
    }
}
