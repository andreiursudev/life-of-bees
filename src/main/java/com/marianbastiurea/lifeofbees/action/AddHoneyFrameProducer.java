package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;

import java.util.ArrayList;
import java.util.List;

public class AddHoneyFrameProducer implements ActionOfTheWeekProducer {
    @Override
    public Object produce(Apiary apiary) {
        List<Integer> hiveIds = new ArrayList<>();
        for (Hive hive : apiary.getHives()) {
            if(hive.getHoneyFrames().canAddANewHoneyFrameInHive())
                hiveIds.add(hive.getId());
        }
        return hiveIds;
    }
}
