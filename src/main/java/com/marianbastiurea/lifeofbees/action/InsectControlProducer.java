package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

//
//public class InsectControlProducer implements ActionOfTheWeekProducer {
//    @Override
//    public Object produce(LifeOfBees lifeOfBees) {
//        Apiary apiary = lifeOfBees.getApiary();
//        BeeTime currentDate = lifeOfBees.getCurrentDate();
//        return apiary.checkInsectControl(currentDate);
//    }
//}


public class InsectControlProducer implements ActionOfTheWeekProducer<Integer> {
    @Override
    public Integer produce(LifeOfBees lifeOfBees) {
        Apiary apiary = lifeOfBees.getApiary();
        BeeTime currentDate = lifeOfBees.getCurrentDate();
        return apiary.checkInsectControl(currentDate);
    }
}
