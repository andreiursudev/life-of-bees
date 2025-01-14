package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;


public class InsectControlProducer implements ActionOfTheWeekProducer<Integer> {
    @Override
    public Integer produce(LifeOfBees lifeOfBees) {
        Apiary apiary = lifeOfBees.getApiary();
        BeeTime currentDate = lifeOfBees.getCurrentDate();
        return apiary.checkInsectControl(currentDate);
    }
}
