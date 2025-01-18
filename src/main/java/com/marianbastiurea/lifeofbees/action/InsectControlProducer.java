package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;


public class InsectControlProducer implements ActionOfTheWeekProducer<Integer> {
    @Override
    public Optional<Integer> produce(LifeOfBees lifeOfBees) {
        Apiary apiary = lifeOfBees.getApiary();
        BeeTime currentDate = lifeOfBees.getCurrentDate();
        Integer result = apiary.checkInsectControl(currentDate);

        // Returnează Optional.empty() dacă rezultatul este 0 sau mai mic decât 1, altfel returnează Optional.of(result)
        return (result > 0) ? Optional.of(result) : Optional.empty();
    }
}
