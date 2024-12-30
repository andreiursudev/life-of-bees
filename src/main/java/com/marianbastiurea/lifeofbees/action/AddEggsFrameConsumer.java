package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;

import java.util.List;
import java.util.function.BiConsumer;

//TODO create custom consumer instead of BiConsumer
public class AddEggsFrameConsumer implements BiConsumer<Apiary, Object> {

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
