package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.util.Optional;


public class InsectControlProducer implements ActionOfTheWeekProducer<Integer> {
    @Override
    public Optional<Integer> produce(Hives hives) {
        BeeTime currentDate = hives.getCurrentDate();
        Integer result = hives.checkInsectControl(currentDate);
        return (result > 0) ? Optional.of(result) : Optional.empty();
    }
}
