package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.List;


public class AddEggsFramesConsumer extends  WeeklyConsumerAbstract <List<Integer>> {

    @Override
    public void accept(Hives hives, List<Integer> eggHiveIds) {
        if (eggHiveIds != null) {
            eggHiveIds.forEach(hiveId -> {
                Hive hive = hives.getHiveById(hiveId);
                if (hive != null) {
                    hive.addNewEggsFrameInHive();
                }
            });
        }
    }
}
