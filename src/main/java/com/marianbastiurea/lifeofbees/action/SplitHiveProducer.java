package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SplitHiveProducer implements ActionOfTheWeekProducer<List<Integer>> {
    @Override
    public Optional<List<Integer>> produce(LifeOfBees lifeOfBees) {
        Apiary apiary = lifeOfBees.getApiary();
        BeeTime currentDate = lifeOfBees.getCurrentDate();
        List<Integer> hiveIds = new ArrayList<>();
        for (Hive hive : apiary.getHives().getHives()) {
            if (hive.checkIfHiveCouldBeSplit(currentDate)) {
                hiveIds.add(hive.getId());
            }
        }
        return hiveIds.isEmpty() ? Optional.empty() : Optional.of(hiveIds);
    }
}
