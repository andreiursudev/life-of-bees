package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

public class HibernateProducer implements ActionOfTheWeekProducer {
    @Override
    public Object produce(LifeOfBees lifeOfBees) {
        Apiary apiary = lifeOfBees.getApiary();
        boolean yearIsChanged = lifeOfBees.isYearIsChanged();
        if (yearIsChanged) {
            return apiary.hibernate();
        } else return null;
    }
}
