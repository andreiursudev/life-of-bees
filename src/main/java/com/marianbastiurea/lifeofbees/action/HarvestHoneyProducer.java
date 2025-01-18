package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class HarvestHoneyProducer implements ActionOfTheWeekProducer<List<Integer>> {

    @Override
    public Optional<List<Integer>> produce(LifeOfBees lifeOfBees) {
        List<Integer> hiveIds = new ArrayList<>();
        Apiary apiary = lifeOfBees.getApiary();

        for (Hive hive : apiary.getHives()) {
            if (hive.isItWasHarvested()) {
                hiveIds.add(hive.getId());
            }
            hive.setItWasHarvested(false);
        }
        return hiveIds.isEmpty() ? Optional.empty() : Optional.of(hiveIds);
    }
}


