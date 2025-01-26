package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddHoneyFramesProducer implements ActionOfTheWeekProducer<List<Integer>> {
    @Override
    public Optional<List<Integer>> produce(Hives hives) {
        List<Integer> hiveIds = new ArrayList<>();
        for (Hive hive : hives.getHives()) {
            if (hive.getHoneyFrames().canAddANewHoneyFrameInHive()) {
                hiveIds.add(hive.getId());
            }
        }
        return hiveIds.isEmpty() ? Optional.empty() : Optional.of(hiveIds);
    }
}

