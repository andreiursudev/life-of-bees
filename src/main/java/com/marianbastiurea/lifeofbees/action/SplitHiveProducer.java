package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.util.ArrayList;
import java.util.List;


public class SplitHiveProducer implements ActionOfTheWeekProducer <List<Integer>> {
    @Override
    public List<Integer> produce(LifeOfBees lifeOfBees) {
        Apiary apiary = lifeOfBees.getApiary();
        BeeTime currentDate = lifeOfBees.getCurrentDate();
        List<Integer> hiveIds = new ArrayList<>();
        for (Hive hive : apiary.getHives()) {
            if (hive.checkIfHiveCouldBeSplit(currentDate))
                hiveIds.add(hive.getId());
        }
        return hiveIds;
    }
}