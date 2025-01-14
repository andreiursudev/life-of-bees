package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.*;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import java.util.*;

//
//public class HarvestHoneyProducer implements ActionOfTheWeekProducer {
//
//    @Override
//    public Object produce(LifeOfBees lifeOfBees) {
//        List<Integer> hiveIds = new ArrayList<>();
//        Apiary apiary = lifeOfBees.getApiary();
//        for (Hive hive : apiary.getHives()) {
//            if (hive.isItWasHarvested())
//                hiveIds.add(hive.getId());
//            hive.setItWasHarvested(false);
//        }
//        return hiveIds;
//    }
//}


public class HarvestHoneyProducer implements ActionOfTheWeekProducer <List<Integer>> {

    @Override
    public List<Integer> produce(LifeOfBees lifeOfBees) {
        List<Integer> hiveIds = new ArrayList<>();
        Apiary apiary = lifeOfBees.getApiary();
        for (Hive hive : apiary.getHives()) {
            if (hive.isItWasHarvested())
                hiveIds.add(hive.getId());
            hive.setItWasHarvested(false);
        }
        return hiveIds;
    }
}
