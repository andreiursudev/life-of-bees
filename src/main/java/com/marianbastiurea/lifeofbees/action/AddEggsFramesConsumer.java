package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;

import java.util.List;

public class AddEggsFramesConsumer implements ActionOfTheWeekConsumer {


    @Override
    public void accept(Apiary apiary, Object data) {
        List<Integer> eggHiveIds = (List<Integer>) data;
        if (eggHiveIds != null) {
            eggHiveIds.forEach(hiveId -> {
                Hive hive = apiary.getHiveById(hiveId);
                if (hive != null) {
                    hive.addNewEggsFrameInHive();
                }
            });
        }
    }
}
