package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.List;


public class SplitHiveConsumer implements ActionOfTheWeekConsumer<List<Integer>> {
    @Override
    public void accept(LifeOfBees lifeOfBees, List<Integer> hiveIds) {

        if (hiveIds != null) {
            hiveIds.forEach(hiveId -> {
                Hive hive =hives.getHiveById(hiveId);
                if (hive != null) {
                   hives.splitHive(hive.getId());
                }
            });
        }
    }
}

