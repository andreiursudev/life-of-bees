package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HarvestHoneyConsumer extends WeeklyConsumerAbstract <List<Integer>> {
    @Override
    public void accept(Hives hives,List<Integer> hiveIds) {
        if (hiveIds != null) {
            hiveIds.forEach(hiveId -> {
                Hive hive = hives.getHiveById(hiveId);
                if (hive != null) {
                    hive.harvestHoney(hives.getCurrentDate());
                }
            });
        }
    }
}


