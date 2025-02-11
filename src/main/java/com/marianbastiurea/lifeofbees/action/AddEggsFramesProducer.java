package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddEggsFramesProducer implements ActionOfTheWeekProducer<List<Integer>> {
    public Optional<List<Integer>> produce(Hives hives) {
        List<Integer> hiveIds = new ArrayList<>();

        for (Hive hive : hives.getHives()) {
            if (hive.getEggFrames().canAddNewEggsFrame())
                hiveIds.add(hive.getId());
        }
        return hiveIds.isEmpty() ? Optional.empty() : Optional.of(hiveIds);
    }

}