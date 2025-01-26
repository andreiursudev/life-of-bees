package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.List;

public class AddHoneyFramesConsumer implements ActionOfTheWeekConsumer<List<Integer>> {


    @Override
    public void accept(Hives hives , List<Integer> honeyHiveIds) {
        if (honeyHiveIds != null) {
            honeyHiveIds.forEach(hiveId -> {
                Hive hive = hives.getHiveById(hiveId);
                if (hive != null) {
                    hive.getHoneyFrames().addNewHoneyFrameInHive();
                }
            });
        }
    }
}