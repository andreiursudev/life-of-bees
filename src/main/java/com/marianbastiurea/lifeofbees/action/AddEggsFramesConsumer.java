package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.List;


public class AddEggsFramesConsumer implements ActionOfTheWeekConsumer<List<Integer>> {

    @Override
    public void accept(LifeOfBees lifeOfBees, List<Integer> eggHiveIds) {
        if (eggHiveIds != null) {
            eggHiveIds.forEach(hiveId -> {
                Hive hive = lifeOfBees.getApiary().getHives().getHiveById(hiveId);
                if (hive != null) {
                    hive.addNewEggsFrameInHive();
                }
            });
        }
    }
}
