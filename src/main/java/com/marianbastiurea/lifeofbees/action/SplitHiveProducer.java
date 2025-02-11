package com.marianbastiurea.lifeofbees.action;


import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SplitHiveProducer implements ActionOfTheWeekProducer<List<Integer>> {
    @Override
    public Optional<List<Integer>> produce(Hives hives) {
        List<Integer> hiveIds = new ArrayList<>();
        for (Hive hive : hives.getHives()) {
            if (hive.checkIfHiveCouldBeSplit(hives.getCurrentDate())) {
                hiveIds.add(hive.getId());
            }
        }
        return hiveIds.isEmpty() ? Optional.empty() : Optional.of(hiveIds);
    }
}
