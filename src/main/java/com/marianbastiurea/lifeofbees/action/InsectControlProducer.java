package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;


import java.util.Optional;


public class InsectControlProducer implements ActionOfTheWeekProducer<Boolean> {
    @Override
    public Optional<Boolean> produce(LifeOfBees lifeOfBees) {
        Apiary apiary = lifeOfBees.getApiary();
        boolean canInsectControl =apiary.getHives().checkInsectControl();
        return canInsectControl ? Optional.of(true) : Optional.empty();
    }
}
