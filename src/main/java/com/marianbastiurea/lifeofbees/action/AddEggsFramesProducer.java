package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddEggsFramesProducer implements ActionOfTheWeekProducer<List<Integer>> {
    public Optional<List<Integer>> produce(LifeOfBees lifeOfBees) {
        List<Integer> hiveIds = new ArrayList<>();
        Apiary apiary = lifeOfBees.getApiary();
        for (Hive hive : apiary.getHives().getHives()) {
            if (hive.getEggFrames().canAddNewEggsFrame())
                hiveIds.add(hive.getId());
        }
        return hiveIds.isEmpty() ? Optional.empty() : Optional.of(hiveIds);
    }

}