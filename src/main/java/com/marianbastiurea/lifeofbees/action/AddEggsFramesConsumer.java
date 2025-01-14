package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.*;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.List;
//
//public class AddEggsFramesConsumer implements ActionOfTheWeekConsumer {

//    @Override
//    public void accept(LifeOfBees lifeOfBees, Object data) {
//        List<Integer> eggHiveIds = (List<Integer>) data;
//        Apiary apiary = lifeOfBees.getApiary();
//        if (eggHiveIds != null) {
//            eggHiveIds.forEach(hiveId -> {
//                Hive hive = apiary.getHiveById(hiveId);
//                if (hive != null) {
//                    hive.addNewEggsFrameInHive();
//                }
//            });
//        }
//    }
//}

public class AddEggsFramesConsumer implements ActionOfTheWeekConsumer<List<Integer>> {

    @Override
    public void accept(LifeOfBees lifeOfBees, List<Integer> eggHiveIds) {
        if (eggHiveIds != null) {
            eggHiveIds.forEach(hiveId -> {
                Hive hive = lifeOfBees.getApiary().getHiveById(hiveId);
                if (hive != null) {
                    hive.addNewEggsFrameInHive();
                }
            });
        }
    }
}
